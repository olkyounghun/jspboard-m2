package com.example.jspboard2.controller;

import com.example.jspboard2.domain.Member;
import com.example.jspboard2.domain.Paging;
import com.example.jspboard2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Resource
    private MemberService memberService;

    
    // 회원가입페이지 이동
    @RequestMapping(value = "/signup", method = {RequestMethod.GET})
    public ModelAndView movesignup(HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        mv.setViewName("signup");

        return mv;
    }

    @RequestMapping(value = "/mailcheck", method = {RequestMethod.GET,RequestMethod.POST})
    public String getMailCheck(@Param("email") String email){

        System.out.println("이메일 인증 요청이 들어옴!");
        System.out.println("이메일 인증 이메일 : " + email);

        return "";
    }

    // 회원가입 정보 입력후 완료
    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public ModelAndView getMembership(@Param("userMember") String userMember,
                                @Param("userPw") String userPw,
                                @Param("userName") String userName,
                                @Param("userGender") String userGender,
                                @Param("userEmail1") String userEmail1,
                                @Param("userEmail2") String userEmail2,
                                @Param("emailChk") String emailChk,
                                HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("loginId");
            session.removeAttribute("loginPw");
            session.invalidate();
            session = request.getSession();
        }

        String userEmailComplet = userEmail1 + "@" + userEmail2;
        List<Member> list;
        list  = memberService.getMembership(userMember,userPw,userName,userGender,userEmailComplet,emailChk);
        Member member = new Member();
        member.setMember(userMember,userPw,userName,userEmailComplet,userGender);
        member.printValue();

        session.setAttribute("loginId",userMember);
        mv.addObject("list",list);
        mv.setViewName("search");
        return mv;
    }

    @RequestMapping(value = "/memberdetail/{id_member}", method = {RequestMethod.GET})
    public ModelAndView memberInformationCheck(@PathVariable("id_member") Integer idMember,
                                     @Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        if (loginId == "null") {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            mv.setViewName("login");
            return mv;
        }

        List<Member> list;
        list = memberService.getMember(idMember);
        mv.addObject("id_member",loginMember.getId_member());
        mv.addObject("list", list);
        mv.setViewName("memberdetail");

        return mv;
    }

    // 개인 회원정보 확인
    @RequestMapping(value = "/membermodify/{id_member}", method = {RequestMethod.POST})
    public ModelAndView memberModify(@Param("id_member") Integer idMember,
                                     @Param("emailMember") String emailMember,
                                     @Param("nameMember") String nameMember,
                                     @Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            bindingResult.reject("loginFail", "로그인 정보가 없습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }
        Member loginMember = memberService.checkLogin(loginId,loginPw);
        if(loginMember.getId_member() != Long.valueOf(idMember)){ // 무결성 검사
            bindingResult.reject("loginFail", "회원정보와 로그인정보가 일치하지않습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }else{
            memberService.modifyMemberDetail(idMember,emailMember,nameMember);
            mv.addObject("error","회원정보수정");
            mv.addObject("errorMessage", "회원정보가 올바르게 '수정' 되었습니다.");
            mv.addObject("errorMove","/search");
            mv.setViewName("error");
        }
        return mv;
    }

    @RequestMapping(value = "/memberdelete/{id_member}", method = {RequestMethod.GET})
        public ModelAndView memberInfoDelete(@PathVariable("id_member") Integer idMember,
                                       @Valid @ModelAttribute Member member,
                                       HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if (loginId == null || loginPw == null) {
            mv.addObject("error","로그인정보");
            mv.addObject("errorMessage", "현재 잘못된 로그인정보 상태입니다.");
            mv.addObject("errorMove","/login");
            mv.setViewName("error");
            return mv;
        }

        memberService.deleteMember(idMember);
        session.invalidate();  // 삭제된 개인 회원세션 로그아웃 하기
        mv.addObject("error","회원정보삭제");
        mv.addObject("errorMessage", "회원정보가 올바르게 '삭제' 되었습니다.");
        mv.addObject("errorMove","/login");
        mv.setViewName("error");
        
        return mv;
    }

    // 매니저 등급 확인이후 매니저게시판으로 이동 및 회원게시판 읽어오기
    @RequestMapping(value="/manager", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView managerLogin(@Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
                                     @RequestParam(value = "page", required = false,defaultValue = "1") int page){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            bindingResult.reject("loginFail", "로그인 정보가 없습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }

        Member userInfo = memberService.checkLogin(loginId,loginPw);

        if(userInfo.getRating_member() == 1){

            Paging paging = new Paging();
            int count = memberService.getAllManager();

            paging.setPage(page);
            paging.setTotalCount(count);
            int beginpage = paging.getBeginPage();
            int endpage = paging.getEndPage();

            List<Member> list;
            list = memberService.getManagerMember(beginpage,endpage,page);
            mv.addObject("list",list);
            mv.addObject("paging", paging);
            mv.setViewName("/manager");
        }else{
            RedirectView redirectView = new RedirectView("/boardlist");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("/boardlist");
        }

        return mv;
    }

    @RequestMapping(value="/manager/{page}", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView managerPageLogin(@Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
                                     @PathVariable(value = "page", required = false) Integer page){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        if ( loginId == null && loginPw == null) {
            bindingResult.reject("loginFail", "로그인 정보가 없습니다.");
            mv.setViewName("redirect:/login");
            return mv;
        }

        Member userInfo = memberService.checkLogin(loginId,loginPw);

        if(userInfo.getRating_member() == 1){


            if(page == null || page <= 0){
                page = 1;
            }
            Paging paging = new Paging();
            int count = memberService.getAllManager();

            paging.setPage(page);
            paging.setTotalCount(count);
            int pagelist = (page-1)*10;

            List<Member> list;
            list = memberService.getManagerPageMember(pagelist);
            mv.addObject("list",list);
            mv.addObject("paging", paging);
            mv.setViewName("/manager");
        }else{
            RedirectView redirectView = new RedirectView("/boardlist");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("/boardlist");
        }

        return mv;
    }

    @RequestMapping(value = "/searchmember",method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getSearchResult(@Valid @ModelAttribute("mv") Member member,
                                        BindingResult bindingResult,
                                        HttpServletRequest request,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        @RequestParam("searchName") String searchName){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        Paging paging = new Paging();
        int count = memberService.getAllSearchManager(startDate,endDate,searchName);
        paging.setPage(page);
        paging.setTotalCount(count);

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Member> list;
        list = memberService.getSearchMember(startDate, endDate, searchName);

        mv.addObject("id_member", loginMember.getId_member());
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("startDate",startDate);
        mv.addObject("endDate",endDate);
        mv.addObject("searchName",searchName);
        mv.setViewName("searchmember");

        return mv;
    }

    @RequestMapping(value = "/searchmember/{page}",method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getSearchPageResult(@Valid @ModelAttribute("mv") Member member,
                                        BindingResult bindingResult,
                                        HttpServletRequest request,
                                        @PathVariable(value = "page", required = false) Integer page,
                                        @RequestParam(value = "startDate", required = false) String startDate,
                                        @RequestParam(value = "endDate", required = false) String endDate,
                                        @RequestParam(value = "searchName", required = false) String searchName){

        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        if(page == null || page <= 0){
            page = 1;
        }
        Paging paging = new Paging();
        int count = memberService.getAllSearchManager(startDate,endDate,searchName);
        paging.setPage(page);
        paging.setTotalCount(count);
        int pagelist = (page-1)*10;

        String loginId = String.valueOf(session.getAttribute("loginId"));
        String loginPw = String.valueOf(session.getAttribute("loginPw"));
        Member loginMember = memberService.checkLogin(loginId,loginPw);

        List<Member> list;
        list = memberService.getSearchPageMember(startDate, endDate, searchName,pagelist);

        mv.addObject("id_member", loginMember.getId_member());
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("startDate",startDate);
        mv.addObject("endDate",endDate);
        mv.addObject("searchName",searchName);
        mv.setViewName("searchmember");

        return mv;
    }

    // 매니저게시판 특정 회원정보 확인
    @GetMapping("/managerinfo/{id_member}")
    public ModelAndView MemberDetail(@PathVariable("id_member") Integer id_member,
                                     @Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        if (bindingResult.hasErrors()) {
            RedirectView redirectView = new RedirectView("/login");
            redirectView.setExposeModelAttributes(false);
            mv.setViewName("login");
            return mv;
        }

        HttpSession session = request.getSession();
        session.getAttribute("userName");

        List<Member> list;
        list = memberService.getMember(id_member);

        mv.addObject("list",list);
        RedirectView redirectView = new RedirectView("/memberinfo");
        redirectView.setExposeModelAttributes(false);
        mv.setViewName("managerinfo");

        return mv;
    }

    // 매니저게시판 특정회원정보 삭제 // 세션 정보를 활용하여 아이디 비밀번호를 저장해야함
    @GetMapping ("managerdelete/{id_member}")
    public ModelAndView deleteMember(@PathVariable("id_member") Integer idMember,
                                     @Valid @ModelAttribute Member member,
                                     BindingResult bindingResult,
                                     HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }
        HttpSession session = request.getSession();
        Object sessionMember = session.getAttribute("userName");
        Member loginMember = memberService.checkLogin((String) sessionMember,(String)sessionMember);

        if(loginMember.getRating_member() != 1 ){ // 둘다 아니라면 로그인
            mv.setViewName("login");
            return mv;
        }else if(loginMember.getRating_member() == 1){ // 관리자가 개인 멤버 삭제시
            memberService.deleteMember(idMember);
            mv.setViewName("manager");
        }

        return mv;
    }
    
}

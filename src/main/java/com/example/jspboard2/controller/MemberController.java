package com.example.jspboard2.controller;

import com.example.jspboard2.domain.*;
import com.example.jspboard2.service.MemberService;
import com.example.jspboard2.service.SessionConst;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @Resource
    private MemberService memberService;

    @Resource
    private LoginService loginService;



    @RequestMapping(value = "/signup", method = {RequestMethod.GET})
    public String movesignup(){


        return "signup";
    }


    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public String getMembership(@Param("userMember") String userMember,
                                @Param("userPw") String userPw,
                                @Param("userName") String userName,
                                @Param("userGender") String userGender,
                                @Param("userEmail1") String userEmail1,
                                @Param("userEmail2") String userEmail2,
                                @Param("emailChk") String emailChk) {

        if(userMember == null || userPw == null || userName == null || userGender == null || userEmail1 == null || userEmail2 == null || emailChk == null){
            return "signup";
        }

        String userEmailComplet = userEmail1 + "@" + userEmail2;
        memberService.getMembership(userMember,userPw,userName,userGender,userEmailComplet,emailChk);

        return "login";
    }

    @GetMapping("/membermodify")
    public ModelAndView movePosting(@Valid @ModelAttribute LoginForm form,
                                    BindingResult bindingResult,
                                    HttpServletRequest request,
                                    @Param("id_member") int idMember){

        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("login");
            return mv;
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        HttpSession session = request.getSession();
        if(loginMember.getRating_member() != 1){
            mv.setViewName("login");
            return mv;
        }
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        List<Member> list;
        list = memberService.getMember(idMember);
        mv.addObject("list",list);
        mv.setViewName("membermodify?id_member="+idMember);

        return mv;
    }


    /*@GetMapping("/login")
    public  String goLogin() {return "login";}

    @PostMapping("/login")
    public String insideLogin(@Param("loignID") String loginID,
                              @Param("loginPw") String loginPw){



        return "";
    }*/

//    @GetMapping("/add")
//    public String addForm(@ModelAttribute("member") Member member) {
//        return "/members/addMemberForm";
//    }
//
//    @PostMapping("/add")
//    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) { // BindingResult ??
//        if (bindingResult.hasErrors()) { // hasErroes ??
//            return "/members/addMemberForm";
//        }
//
//        memberRepository.save(member);
//        return "redirect:/";
//    }
/** 세션 어노테이션을 확인해서 다시 시도해보자 */



}

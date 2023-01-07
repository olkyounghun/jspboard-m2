package com.example.jspboard2.controller;

import com.example.jspboard2.service.SearchService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class SearchController {

    @Resource
    private SearchService searchService;

    @RequestMapping(value = "/searchworld", method = RequestMethod.POST)
    public String getSearchContent(@Param("startword") String startword){

        /** 첫 시도는 클래스내에 'switch'를 활용하여 검색하는 내용에 따라 페이지 리턴을 목표했었는데
         * DB를 활용하여 해당 내용의 리턴칼럼에 페이지내용을 담아 돌려주는것이 더 좋은활용방법인거 같아
         * Mapper를 활용하여 페이지를 돌려주는 테이블을 만들었습니다.*/
        /** Search 테이블을 만든이후 Mapper를 활용하려했더니 Board테이블과 동일한 Mapper를 활용하지못하니
         * 새로운 SearchMapper 와 서비스들을 만들어줘야했습니다. */

        /** 처음에는 단순히 검색내용에 맞추어 해당 검색결과값과 같은 페이지를 리턴하여
         * 페이지를 보여주는 형식을 생각했는데 진행에있어서 차질이 생긴다.
         * 여러 수정을 거치며 이해하는 과정을 겪고있음.
         */
        String result = "redirect:/";
        String sql = searchService.getSearchContent(startword);
        if(sql != null){
            result += sql;
        }
        return result;
    }
}

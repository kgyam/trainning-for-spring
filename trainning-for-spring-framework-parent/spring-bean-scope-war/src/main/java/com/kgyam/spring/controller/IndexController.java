package com.kgyam.spring.controller;

import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kg yam
 * @date 2021-01-25 15:28
 * @since
 */
@Controller
public class IndexController {

    @Autowired
    @Qualifier("requestScopeUser")
    private User requestScopeUser;

    @Autowired
    @Qualifier("sessionScopeUser")
    private User sessionScopeUser;


    @Autowired
    @Qualifier("applicationScopeUser")
    private User applicationScopeUser;

    @RequestMapping("/index.html")
    public String hello(Model model) {
        model.addAttribute ("requestScopeUser", requestScopeUser);
        model.addAttribute ("sessionScopeUser", sessionScopeUser);
        model.addAttribute ("applicationScopeUser", applicationScopeUser);
        System.out.println ("finish");
        return "index";
    }

}

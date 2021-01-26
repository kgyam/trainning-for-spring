package com.kgyam.spring.controller;

import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

/**
 * @author kg yam
 * @date 2021-01-25 15:28
 * @since
 */
@RestController
public class IndexController {

    @Autowired
    private User user;

    @RequestMapping("/index.html")
    public String hello(Model model) {
        model.addAttribute ("user", user);
        return "index";
    }

}

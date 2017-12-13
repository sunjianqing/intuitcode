package com.jianqing.intuit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jianqingsun on 12/13/17.
 */
@Controller
public class IntuitController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("pageTitle","Welcome to my Awesome Dynamic Application");
        return "index";
    }
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = {"/Login", "/dashboard"})
    public String forwardToReact() {
        return "forward:/index.html"; // React의 index.html로 리다이렉트
    }
}

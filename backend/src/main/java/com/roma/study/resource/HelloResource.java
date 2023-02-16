package com.roma.study.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/hello")
@Controller
public class HelloResource {

    @GetMapping("/test")
    public String hello(){
        System.out.println("hello world!!");
        return "hello world";
    }
}

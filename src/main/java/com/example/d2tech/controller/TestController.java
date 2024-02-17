package com.example.d2tech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping({"/","/home","/status"})
    public String getStatus(){
        return "This controller is working. \n Link on the lesson: https://www.youtube.com/watch?v=E2ScpMM97l4&list=PLA7e3zmT6XQX6uZ2kC5R7exF0glolxs_c";
    }
}

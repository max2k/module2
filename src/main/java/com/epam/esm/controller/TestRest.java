package com.epam.esm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

    @GetMapping("/data")
    public @ResponseBody String getSimpleData(){
        return "Hi!";
    }
}

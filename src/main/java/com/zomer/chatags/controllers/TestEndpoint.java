package com.zomer.chatags.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndpoint {
    @GetMapping("/test")
    public String test(){
        return "Servidor Corriendo.....";
    }
}

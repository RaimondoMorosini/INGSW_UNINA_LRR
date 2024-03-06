package com.lrr.Dieti23Server.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/utente")
    public String getUser(){

        //lettura db..

        return "nome utente";
    }
}

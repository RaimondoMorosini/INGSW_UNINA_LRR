package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class UserControllerRest {

    @GetMapping(path = "/user")
    public String getuser(){
        return "Acces Granted : get User details";
    }

    @PostMapping(path = "/user") 
    public String updateUser(){
        return "Acces Granted : update User details";
    }

}

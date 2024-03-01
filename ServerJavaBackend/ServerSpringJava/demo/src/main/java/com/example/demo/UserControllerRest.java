package com.example.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerRest {

    @GetMapping(path = "/user")
    @PreAuthorize("hasAnyAuthority('SCOPE_readuser')")
    public String getuser(){
        return "Acces Granted : get User details";
    }

    @PostMapping(path = "/user")
    @PreAuthorize("hasAnyAuthority('SCOPE_updateuser')")
    public String updateUser(){
        return "Acces Granted : update User details";
    }

}

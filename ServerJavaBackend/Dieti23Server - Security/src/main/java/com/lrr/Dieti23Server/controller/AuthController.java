package com.lrr.Dieti23Server.controller;

import com.lrr.Dieti23Server.dto.CredenzialiUtenteDTO;
import com.lrr.Dieti23Server.dto.ReqRes;
import com.lrr.Dieti23Server.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/login")
    public String loginGenToken(@RequestBody CredenzialiUtenteDTO credenzialiInserite){
        return (authService.login(credenzialiInserite));
    }
}

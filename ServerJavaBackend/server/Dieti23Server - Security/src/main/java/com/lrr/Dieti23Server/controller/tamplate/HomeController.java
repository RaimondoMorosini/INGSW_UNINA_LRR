package com.lrr.Dieti23Server.controller.tamplate;

import com.lrr.Dieti23Server.model.UserTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(UserTest user, @AuthenticationPrincipal OidcUser principal) {

        return "index";
    }
}

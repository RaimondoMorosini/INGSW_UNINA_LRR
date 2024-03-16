package api.dieti2024.controller;

import api.dieti2024.dto.CredenzialiUtenteDTO;
import api.dieti2024.dto.ReqRes;
import api.dieti2024.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/testhello")
    public String testHello(){
        return "Hello";
    }
    @PostMapping("/signup")
    public String genTokenAfterSingUp(@RequestBody CredenzialiUtenteDTO signUpRequest){
        return authService.registrazione(signUpRequest);
    }
    @PostMapping("/login")
    public String GenTokenBylogin(@RequestBody CredenzialiUtenteDTO credenzialiInserite){
        return (authService.login(credenzialiInserite));
    }


    @PostMapping("/SalvaTest")
    public String salvaTest(){
        authService.saveTest();
        return "Test salvato";
    }
}

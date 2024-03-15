package api.dieti2024.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvaController {

    @GetMapping("/prova/hello")
    public String hello(){
        return "Hello";
    }
}

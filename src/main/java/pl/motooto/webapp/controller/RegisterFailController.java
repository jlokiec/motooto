package pl.motooto.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterFailController {
    @GetMapping("/register_fail")
    public String initializeView() {
        return "register_fail";
    }
}

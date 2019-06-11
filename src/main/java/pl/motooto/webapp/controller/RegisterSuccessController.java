package pl.motooto.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterSuccessController {
    @GetMapping("/register_success")
    public String initializeView() {
        return "register_success";
    }
}

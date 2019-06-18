package pl.motooto.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterSuccessController {
    private static final String REGISTERED_NAME = "registeredName";

    @GetMapping("/register_success")
    public String initializeView(@RequestParam String registeredName, Model model) {
        model.addAttribute(REGISTERED_NAME, registeredName);
        return "register_success";
    }
}

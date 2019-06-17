package pl.motooto.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ValidationError {

    @GetMapping("/validation_error")
    public String initializeView() { return "validation_error"; }
}

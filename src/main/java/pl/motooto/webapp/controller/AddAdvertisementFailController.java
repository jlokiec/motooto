package pl.motooto.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddAdvertisementFailController {
    @GetMapping("/add_advert_fail")
    public String initializeView() {
        return "add_advert_fail";
    }
}

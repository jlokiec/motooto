package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.motooto.webapp.model.dto.UserDto;
import pl.motooto.webapp.service.UserService;
import pl.motooto.webapp.service.exception.EmailTakenException;
import pl.motooto.webapp.service.exception.PasswordsDontMatchException;
import pl.motooto.webapp.service.exception.UsernameTakenException;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private static final String USER_DTO = "userDto";

    private UserService userService;

    @Autowired
    RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String initializeView(Model model) {
        model.addAttribute(USER_DTO, new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView register(@Valid @ModelAttribute(USER_DTO) UserDto userDto, BindingResult result, Model model) {
        RedirectView redirectView = new RedirectView();

        if (result.hasErrors()) {
            redirectView.setUrl("/register_fail");
            return redirectView;
        }

        try {
            userService.registerNewUser(userDto);
        } catch (UsernameTakenException | EmailTakenException | PasswordsDontMatchException e) {
            redirectView.setUrl("/register_fail");
            return redirectView;
        }

        redirectView.addStaticAttribute("registeredName", userDto.getFirstName());
        redirectView.setUrl("/register_success");

        return redirectView;
    }
}

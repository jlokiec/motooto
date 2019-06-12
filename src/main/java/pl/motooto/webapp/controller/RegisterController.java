package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.motooto.webapp.service.exception.EmailTakenException;
import pl.motooto.webapp.service.exception.UsernameTakenException;
import pl.motooto.webapp.model.dto.UserDto;
import pl.motooto.webapp.service.UserService;

import javax.validation.Valid;
import java.util.List;

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
    public String register(@Valid @ModelAttribute(USER_DTO) UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();

            for (ObjectError error : errors) {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                }
            }

            System.out.println("Some errors");
        }

        try {
            userService.registerNewUser(userDto);
        } catch (UsernameTakenException e) {
            System.out.println("username taken");
            return "/register_fail";
        } catch (EmailTakenException e) {
            return "/register_fail";
        }

        return "/register_success";
    }
}

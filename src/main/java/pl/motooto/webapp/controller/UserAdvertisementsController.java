package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.motooto.webapp.model.dto.UserAdvertisementsDto;
import pl.motooto.webapp.service.UserAdvertisementsService;
import pl.motooto.webapp.service.exception.UserNotLoggedInException;

@Controller
public class UserAdvertisementsController {
    private UserAdvertisementsService userAdvertisementsService;

    @Autowired
    public UserAdvertisementsController(UserAdvertisementsService userAdvertisementsService) {
        this.userAdvertisementsService = userAdvertisementsService;
    }

    @GetMapping("/user_advertisements")
    public String initializeView(Model model) throws UserNotLoggedInException {
        UserAdvertisementsDto userAdvertisementsDto = null;

        try {
            model.addAttribute("advertisements", userAdvertisementsService.getUserAdvertisements());
        } catch (Exception e) {
            throw new UserNotLoggedInException();
        }

        return "user_advertisements";
    }
}

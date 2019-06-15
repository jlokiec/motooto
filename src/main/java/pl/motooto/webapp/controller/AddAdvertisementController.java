package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.motooto.webapp.model.dto.AddAdvertisementDto;
import pl.motooto.webapp.service.AddAdvertisementService;

import javax.validation.Valid;

@Controller
public class AddAdvertisementController {
    private static final String ADD_ADVERTISEMENT_DTO = "addAdvertisementDto";

    private AddAdvertisementService addAdvertisementService;

    @Autowired
    AddAdvertisementController(AddAdvertisementService addAdvertisementService) {
        this.addAdvertisementService = addAdvertisementService;
    }

    @GetMapping("/add_advert")
    public String initializeView(Model model) {
        model.addAttribute(ADD_ADVERTISEMENT_DTO, new AddAdvertisementDto());
        return "add_advert";
    }

    @PostMapping("/add_advert")
    public RedirectView addNewAdvertisement(@Valid @ModelAttribute(ADD_ADVERTISEMENT_DTO) AddAdvertisementDto addAdvertisementDto, BindingResult result, Model model) {
        RedirectView redirectView = new RedirectView();

        try {
            addAdvertisementService.addNewAdvertisement(addAdvertisementDto);
        } catch (Exception e) {
            redirectView.setUrl("/add_advert_fail");
            return redirectView;
        }

        redirectView.setUrl("/add_advert");

        return redirectView;
    }
}

package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.dto.EditAdvertisementDto;
import pl.motooto.webapp.service.AdvertisementService;
import pl.motooto.webapp.service.EditAdvertisementService;

import javax.validation.Valid;

@Controller
public class EditAdvertisementController {
    private static final String EDIT_ADVERTISEMENT_DTO = "editAdvertisementDto";

    private AdvertisementService advertisementService;
    private EditAdvertisementService editAdvertisementService;
    private long advertisementId;

    @Autowired
    EditAdvertisementController(AdvertisementService advertisementService, EditAdvertisementService editAdvertisementService) {
        this.advertisementService = advertisementService;
        this.editAdvertisementService = editAdvertisementService;
    }

    @GetMapping("/edit_advertisement")
    public String initializeView(@RequestParam long advertisementId, Model model) {
        this.advertisementId = advertisementId;

        Advertisement advertisement = null;

        try {
            advertisement = advertisementService.getAdvertisement(advertisementId);
        } catch (Exception e) {
            return "/user_advertisements";
        }

        EditAdvertisementDto editAdvertisementDto = new EditAdvertisementDto();
        editAdvertisementDto.setTitle(advertisement.getTitle());
        editAdvertisementDto.setDescription(advertisement.getDescription());
        editAdvertisementDto.setPhoneNumber(advertisement.getPhoneNumber());
        editAdvertisementDto.setPrice(advertisement.getPrice());
        editAdvertisementDto.setCarMake(advertisement.getDetails().getMake());
        editAdvertisementDto.setCarModel(advertisement.getDetails().getModel());
        editAdvertisementDto.setProductionYear(advertisement.getDetails().getProductionYear());
        editAdvertisementDto.setEngineDisplacement(advertisement.getDetails().getEngineDisplacement());
        editAdvertisementDto.setHorsePower(advertisement.getDetails().getHorsePower());
        editAdvertisementDto.setDamaged(advertisement.getDetails().isDamaged());

        model.addAttribute(EDIT_ADVERTISEMENT_DTO, editAdvertisementDto);

        return "edit_advertisement";
    }

    @PostMapping("/edit_advertisement")
    public RedirectView updateAdvertisement(@Valid @ModelAttribute(EDIT_ADVERTISEMENT_DTO) EditAdvertisementDto editAdvertisementDto, BindingResult result, Model model) {
        RedirectView redirectView = new RedirectView();

        try {
            editAdvertisementService.updateAdvertisement(advertisementId, editAdvertisementDto);
        } catch (Exception e) {
            redirectView.setUrl("/user_advertisements");
            return redirectView;
        }

        redirectView.setUrl("/user_advertisements");

        return redirectView;
    }
}

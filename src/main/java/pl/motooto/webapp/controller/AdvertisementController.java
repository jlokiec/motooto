package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.service.AdvertisementService;
import pl.motooto.webapp.service.exception.AdvertisementNotFoundException;

@Controller
public class AdvertisementController {
    private static final String ADVERTISEMENT = "advertisement";

    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/advertisement")
    public String initializeView(@RequestParam long advertisementId, Model model) {
        model.addAttribute(ADVERTISEMENT, advertisementId);
        try {
            Advertisement advertisement = advertisementService.getAdvertisement(advertisementId);
            System.out.println(advertisement);
        } catch (AdvertisementNotFoundException e) {
            System.out.println("Advertisement not found");
        }
        return "advertisement";
    }
}

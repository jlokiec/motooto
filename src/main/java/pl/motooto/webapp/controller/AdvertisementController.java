package pl.motooto.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.dto.AdvertisementPresentationDto;
import pl.motooto.webapp.service.AdvertisementConverterService;
import pl.motooto.webapp.service.AdvertisementService;
import pl.motooto.webapp.service.exception.AdvertisementNotFoundException;

@Controller
public class AdvertisementController {
    private static final String TITLE = "title";
    private static final String PUBLISHER_USERNAME = "publisherUsername";
    private static final String LAST_MODIFICATION_DATE = "lastModificationDate";
    private static final String PRICE_PLN = "pricePln";
    private static final String PRICE_EUR = "priceEur";
    private static final String PRICE_USD = "priceUsd";
    private static final String PRICE_GBP = "priceGpb";
    private static final String DESCRIPTION = "description";
    private static final String CAR_MAKE = "carMake";
    private static final String CAR_MODEL = "carModel";
    private static final String PRODUCTION_YEAR = "productionYear";
    private static final String ENGINE_DISPLACEMENT = "engineDisplacement";
    private static final String HORSE_POWER = "horsePower";
    private static final String CAR_STATUS = "carStatus";
    private static final String PHONE_NUMBER = "phoneNumber";

    private AdvertisementService advertisementService;
    private AdvertisementConverterService advertisementConverterService;

    @Autowired
    public AdvertisementController(
            AdvertisementService advertisementService,
            AdvertisementConverterService advertisementConverterService) {
        this.advertisementService = advertisementService;
        this.advertisementConverterService = advertisementConverterService;
    }

    @GetMapping("/advertisement")
    public String initializeView(@RequestParam long advertisementId, Model model) {
        AdvertisementPresentationDto advertisementDto = null;

        try {
            Advertisement advertisement = advertisementService.getAdvertisement(advertisementId);
            advertisementDto = advertisementConverterService.convertAdvertisement(advertisement);
        } catch (AdvertisementNotFoundException e) {
            return "advertisement_not_found";
        }

        model.addAttribute(TITLE, advertisementDto.getTitle());
        model.addAttribute(PUBLISHER_USERNAME, advertisementDto.getUsername());
        model.addAttribute(LAST_MODIFICATION_DATE, advertisementDto.getLastModificationDate());
        model.addAttribute(PRICE_PLN, advertisementDto.getPriceInPln());
        model.addAttribute(PRICE_EUR, advertisementDto.getPriceInEur());
        model.addAttribute(PRICE_USD, advertisementDto.getPriceInUsd());
        model.addAttribute(PRICE_GBP, advertisementDto.getPriceInGbp());
        model.addAttribute(DESCRIPTION, advertisementDto.getDescription());
        model.addAttribute(CAR_MAKE, advertisementDto.getCarMake());
        model.addAttribute(CAR_MODEL, advertisementDto.getCarModel());
        model.addAttribute(PRODUCTION_YEAR, advertisementDto.getProductionYear());
        model.addAttribute(ENGINE_DISPLACEMENT, advertisementDto.getEngineDisplacement());
        model.addAttribute(HORSE_POWER, advertisementDto.getHorsePower());
        model.addAttribute(CAR_STATUS, advertisementDto.getCarStatus());
        model.addAttribute(PHONE_NUMBER, advertisementDto.getPhoneNumber());

        return "advertisement";
    }
}

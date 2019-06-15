package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.dto.AdvertisementPresentationDto;
import pl.motooto.webapp.restapi.model.ExchangeRate;
import pl.motooto.webapp.service.exception.InvalidCurrencyCodeException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdvertisementConverterService {
    private static final String MODIFICATION_DATE_FORMAT = "dd.MM.yyyy HH:mm";
    private static final String DOUBLE_PRECISION = "%.2f";
    private static final String EUR_CODE = "EUR";
    private static final String USD_CODE = "USD";
    private static final String GBP_CODE = "GBP";

    private NbpCurrencyService nbpCurrencyService;

    @Autowired
    public AdvertisementConverterService(NbpCurrencyService nbpCurrencyService) {
        this.nbpCurrencyService = nbpCurrencyService;
    }

    public AdvertisementPresentationDto convertAdvertisement(Advertisement advertisement) {
        AdvertisementPresentationDto convertedAdvertisement = new AdvertisementPresentationDto();

        String title = advertisement.getTitle();
        convertedAdvertisement.setTitle(title);

        String username = advertisement.getPublisher().getUsername();
        convertedAdvertisement.setUsername(username);

        long lastModificationTimestamp = advertisement.getLastModificationTimestamp();
        String lastModificationDate = convertTimestampToDateString(lastModificationTimestamp);
        convertedAdvertisement.setLastModificationDate(lastModificationDate);

        double priceInPln = advertisement.getPrice();
        String priceInPlnString = formatDoubleToString(priceInPln);
        convertedAdvertisement.setPriceInPln(priceInPlnString);

        String priceInEur = convertPriceToCurrency(priceInPln, EUR_CODE);
        convertedAdvertisement.setPriceInEur(priceInEur);

        String priceInUsd = convertPriceToCurrency(priceInPln, USD_CODE);
        convertedAdvertisement.setPriceInUsd(priceInUsd);

        String priceInGpb = convertPriceToCurrency(priceInPln, GBP_CODE);
        convertedAdvertisement.setPriceInGbp(priceInGpb);

        String description = advertisement.getDescription();
        convertedAdvertisement.setDescription(description);

        String carMake = advertisement.getDetails().getMake();
        convertedAdvertisement.setCarMake(carMake);

        String carModel = advertisement.getDetails().getModel();
        convertedAdvertisement.setCarModel(carModel);

        int productionYear = advertisement.getDetails().getProductionYear();
        convertedAdvertisement.setProductionYear(productionYear);

        int engineDisplacement = advertisement.getDetails().getEngineDisplacement();
        convertedAdvertisement.setEngineDisplacement(engineDisplacement);

        int horsePower = advertisement.getDetails().getHorsePower();
        convertedAdvertisement.setHorsePower(horsePower);

        boolean carDamaged = advertisement.getDetails().isDamaged();
        String carStatus = convertCarStatus(carDamaged);
        convertedAdvertisement.setCarStatus(carStatus);

        String phoneNumber = advertisement.getPhoneNumber();
        convertedAdvertisement.setPhoneNumber(phoneNumber);

        return convertedAdvertisement;
    }

    private String convertTimestampToDateString(long timestamp) {
        Date date = new Date(timestamp);
        DateFormat dateFormat = new SimpleDateFormat(MODIFICATION_DATE_FORMAT);
        String formattedDate = dateFormat.format(date);

        return formattedDate;
    }

    private String convertPriceToCurrency(double price, String currencyCode) {
        ExchangeRate exchangeRate = null;
        try {
            exchangeRate = nbpCurrencyService.getExchangeRate(currencyCode);
        } catch (IOException | InvalidCurrencyCodeException e) {
            return null;
        }

        double exchangeRateValue = exchangeRate.getRateValue();
        double convertedValue = price / exchangeRateValue;
        return formatDoubleToString(convertedValue);
    }

    private String formatDoubleToString(double number) {
        return String.format(DOUBLE_PRECISION, number);
    }

    private String convertCarStatus(boolean damaged) {
        if (damaged) {
            return "uszkodzony";
        } else {
            return "sprawny";
        }
    }
}

package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.dto.AdvertisementPresentationDto;
import pl.motooto.webapp.model.dto.AdvertisementPresentationDtoBuilder;
import pl.motooto.webapp.restapi.model.ExchangeRate;
import pl.motooto.webapp.service.exception.InvalidCurrencyCodeException;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

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
        String title = advertisement.getTitle();
        String username = advertisement.getPublisher().getUsername();
        long lastModificationTimestamp = advertisement.getLastModificationTimestamp();
        String lastModificationDate = convertTimestampToDateString(lastModificationTimestamp);

        double priceInPln = advertisement.getPrice();
        String priceInPlnString = formatDoubleToString(priceInPln);
        String priceInEur = convertPriceToCurrency(priceInPln, EUR_CODE);
        String priceInUsd = convertPriceToCurrency(priceInPln, USD_CODE);
        String priceInGpb = convertPriceToCurrency(priceInPln, GBP_CODE);

        String description = advertisement.getDescription();
        String carMake = advertisement.getDetails().getMake();
        String carModel = advertisement.getDetails().getModel();
        int productionYear = advertisement.getDetails().getProductionYear();
        int engineDisplacement = advertisement.getDetails().getEngineDisplacement();

        int horsePower = advertisement.getDetails().getHorsePower();

        boolean carDamaged = advertisement.getDetails().isDamaged();
        String carStatus = convertCarStatus(carDamaged);

        String phoneNumber = advertisement.getPhoneNumber();

        AdvertisementPresentationDto convertedAdvertisement = new AdvertisementPresentationDtoBuilder()
                .setTitle(title)
                .setUsername(username)
                .setLastModificationDate(lastModificationDate)
                .setPriceInPln(priceInPlnString)
                .setPriceInEur(priceInEur)
                .setPriceInUsd(priceInUsd)
                .setPriceInGbp(priceInGpb)
                .setDescription(description)
                .setCarMake(carMake)
                .setCarModel(carModel)
                .setProductionYear(productionYear)
                .setEngineDisplacement(engineDisplacement)
                .setHorsePower(horsePower)
                .setCarStatus(carStatus)
                .setPhoneNumber(phoneNumber)
                .build();

        return convertedAdvertisement;
    }

    private String convertTimestampToDateString(long timestamp) {
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(MODIFICATION_DATE_FORMAT, Locale.getDefault());
        return dateFormatter.format(date);
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

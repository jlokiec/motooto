package pl.motooto.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.CarDetails;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.model.dto.AdvertisementPresentationDto;
import pl.motooto.webapp.restapi.model.ExchangeRate;
import pl.motooto.webapp.service.AdvertisementConverterService;
import pl.motooto.webapp.service.NbpCurrencyService;
import pl.motooto.webapp.service.exception.InvalidCurrencyCodeException;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class AdvertisementConverterServiceTest {
    private static final String AD_TITLE = "Sprzedam Opla!";
    private static final String USERNAME = "johnsmith";
    private static final long MODIFICATION_TIMESTAMP = 1559403503000L;
    private static final double PRICE = 12345.67;
    private static final String CAR_MAKE = "Opel";
    private static final String CAR_MODEL = "Kadett";
    private static final int HORSE_POWER = 75;
    private static final int PRODUCTION_YEAR = 1988;
    private static final int ENGINE_DISPLACEMENT = 1598;
    private static final String PHONE_NUMBER = "123-456-789";
    private static final String AD_DESCRIPTION = "Auto sprawne, stan jak na foto, polecam!:)";

    private static AdvertisementConverterService service;
    private static Advertisement advertisement;

    @BeforeClass
    public static void setup() throws IOException, InvalidCurrencyCodeException {
        NbpCurrencyService nbpService = mock(NbpCurrencyService.class);

        ExchangeRate eurExchangeRate = new ExchangeRate("EUR", new Date(), 4.2756);
        when(nbpService.getExchangeRate("EUR")).thenReturn(eurExchangeRate);

        ExchangeRate usdExchangeRate = new ExchangeRate("USD", new Date(), 3.7463);
        when(nbpService.getExchangeRate("USD")).thenReturn(usdExchangeRate);

        ExchangeRate gbpExchangeRate = new ExchangeRate("GBP", new Date(), 5.3834);
        when(nbpService.getExchangeRate("GBP")).thenReturn(gbpExchangeRate);

        service = new AdvertisementConverterService(nbpService);

        setupAdvertisement();
    }

    @Test
    public void testConvertAdvertisementTitle() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(AD_TITLE, ad.getTitle());
    }

    @Test
    public void testConvertAdvertisementUsername() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(USERNAME, ad.getUsername());
    }

    @Test
    public void testConvertAdvertisementLastModificationDate() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);

        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(MODIFICATION_TIMESTAMP), TimeZone.getDefault().toZoneId());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.getDefault());
        String expectedDate = dateFormatter.format(date);

        assertEquals(expectedDate, ad.getLastModificationDate());
    }

    @Test
    public void testConvertAdvertisementPriceInPln() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(String.format("%.2f", 12345.67), ad.getPriceInPln());
    }

    @Test
    public void testConvertAdvertisementPriceInEur() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(String.format("%.2f", 2887.47), ad.getPriceInEur());
    }

    @Test
    public void testConvertAdvertisementPriceInUsd() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(String.format("%.2f", 3295.43), ad.getPriceInUsd());
    }

    @Test
    public void testConvertAdvertisementPriceInGbp() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(String.format("%.2f", 2293.28), ad.getPriceInGbp());
    }

    @Test
    public void testConvertAdvertisementDescription() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(AD_DESCRIPTION, ad.getDescription());
    }

    @Test
    public void testConvertAdvertisementCarMake() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(CAR_MAKE, ad.getCarMake());
    }

    @Test
    public void testConvertAdvertisementCarModel() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(CAR_MODEL, ad.getCarModel());
    }

    @Test
    public void testConvertAdvertisementProductionYear() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(PRODUCTION_YEAR, ad.getProductionYear());
    }

    @Test
    public void testConvertAdvertisementEngineDisplacement() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(ENGINE_DISPLACEMENT, ad.getEngineDisplacement());
    }

    @Test
    public void testConvertAdvertisementHorsePower() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(HORSE_POWER, ad.getHorsePower());
    }

    @Test
    public void testConvertAdvertisementCarStatus() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals("sprawny", ad.getCarStatus());
    }

    @Test
    public void testConvertAdvertisementPhoneNumber() {
        AdvertisementPresentationDto ad = service.convertAdvertisement(advertisement);
        assertEquals(PHONE_NUMBER, ad.getPhoneNumber());
    }

    private static void setupAdvertisement() {
        User publisher = new User();
        publisher.setUsername(USERNAME);
        publisher.setFirstName("John");
        publisher.setLastName("Smith");

        CarDetails details = new CarDetails();
        details.setDamaged(false);
        details.setMake(CAR_MAKE);
        details.setModel(CAR_MODEL);
        details.setEngineDisplacement(ENGINE_DISPLACEMENT);
        details.setHorsePower(HORSE_POWER);
        details.setProductionYear(PRODUCTION_YEAR);

        advertisement = new Advertisement();
        advertisement.setPublisher(publisher);
        advertisement.setTitle(AD_TITLE);
        advertisement.setActive(true);
        advertisement.setDetails(details);
        advertisement.setLastModificationTimestamp(MODIFICATION_TIMESTAMP);
        advertisement.setPrice(PRICE);
        advertisement.setDescription(AD_DESCRIPTION);
        advertisement.setPhoneNumber(PHONE_NUMBER);
    }
}

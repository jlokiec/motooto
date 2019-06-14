package pl.motooto.webapp.restapi.model;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ExchangeRate {
    private String currencyCode;
    private Date rateDate;
    private double rateValue;

    public ExchangeRate(ExchangeRateResponse mappedResponse) {
        currencyCode = mappedResponse.getCurrencyCode();
        CurrencyExchangeRate currencyExchangeRate = mappedResponse.getExchangeRates().get(0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(currencyExchangeRate.getRateDate());
        } catch (ParseException e) {
            parsedDate = new Date();
        }
        rateDate = parsedDate;
        rateValue = currencyExchangeRate.getRateValue();
    }
}

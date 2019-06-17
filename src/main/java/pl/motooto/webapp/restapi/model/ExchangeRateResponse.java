package pl.motooto.webapp.restapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ExchangeRateResponse {
    @SerializedName("code")
    private String currencyCode;
    @SerializedName("rates")
    private List<CurrencyExchangeRate> exchangeRates;
}

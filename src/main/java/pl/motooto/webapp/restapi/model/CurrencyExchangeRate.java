package pl.motooto.webapp.restapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CurrencyExchangeRate {
    @SerializedName("effectiveDate")
    private String rateDate;
    @SerializedName("mid")
    private double rateValue;
}

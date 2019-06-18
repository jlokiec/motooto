package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.restapi.api.ExchangeRateApi;
import pl.motooto.webapp.restapi.model.ExchangeRate;
import pl.motooto.webapp.restapi.model.ExchangeRateResponse;
import pl.motooto.webapp.service.exception.InvalidCurrencyCodeException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Service
public class NbpCurrencyService {
    private static final String TABLE_A = "a";

    private ExchangeRateApi exchangeRateApi;

    @Autowired
    public NbpCurrencyService(Retrofit retrofit) {
        exchangeRateApi = retrofit.create(ExchangeRateApi.class);
    }

    public ExchangeRate getExchangeRate(String currencyCode) throws IOException, InvalidCurrencyCodeException {
        Call<ExchangeRateResponse> nbpApiCall = exchangeRateApi.getExchangeRate(TABLE_A, currencyCode);
        Response<ExchangeRateResponse> response = nbpApiCall.execute();

        if (response.code() == 404) {
            throw new InvalidCurrencyCodeException();
        }

        ExchangeRateResponse mappedResponse = response.body();
        ExchangeRate exchangeRate = new ExchangeRate(mappedResponse);

        return exchangeRate;
    }
}

package pl.motooto.webapp.restapi.api;

import pl.motooto.webapp.restapi.model.ExchangeRateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ExchangeRateApi {
    @Headers("Accept: application/json")
    @GET("{table}/{currencyCode}")
    Call<ExchangeRateResponse> getExchangeRate(@Path("table") String table, @Path("currencyCode") String currencyCode);
}

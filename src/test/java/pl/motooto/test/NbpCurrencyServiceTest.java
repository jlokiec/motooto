package pl.motooto.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.motooto.test.restapi.NbpApiInterceptor;
import pl.motooto.webapp.restapi.model.CurrencyCode;
import pl.motooto.webapp.restapi.model.ExchangeRate;
import pl.motooto.webapp.service.NbpCurrencyService;
import pl.motooto.webapp.service.exception.InvalidCurrencyCodeException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class NbpCurrencyServiceTest {
    private static final String VALID_EUR_CODE = CurrencyCode.EUR.toString();
    private static final String INVALID_CURRENCY_CODE = "XD";

    private static NbpCurrencyService currencyService;
    private static Date expectedRateDate;

    @BeforeClass
    public static void setup() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new NbpApiInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nbp.pl/api/exchangerates/rates/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        currencyService = new NbpCurrencyService(retrofit);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.JANUARY, 2, 0, 0, 0);
        calendar.clear(Calendar.MILLISECOND);
        expectedRateDate = calendar.getTime();
    }

    @Test
    public void testGetExchangeRateShouldPass() throws IOException, InvalidCurrencyCodeException {
        ExchangeRate result = currencyService.getExchangeRate(VALID_EUR_CODE);
        assertEquals(VALID_EUR_CODE, result.getCurrencyCode());
        expectedRateDate.compareTo(result.getRateDate());
        assertEquals(0, expectedRateDate.compareTo(result.getRateDate()));
        assertEquals(1.2345, result.getRateValue(), 1e-10);
    }

    @Test(expected = InvalidCurrencyCodeException.class)
    public void testGetExchangeRateInvalidCurrencyCode() throws IOException, InvalidCurrencyCodeException {
        currencyService.getExchangeRate(INVALID_CURRENCY_CODE);
    }
}

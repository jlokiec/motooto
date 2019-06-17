package pl.motooto.webapp.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {
    private static final String BASE_URL = "http://api.nbp.pl/api/exchangerates/rates/";

    private Retrofit retrofit;

    public RetrofitConfig() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Bean
    public Retrofit getRetrofit() {
        return retrofit;
    }
}

package pl.motooto.test.restapi;

import okhttp3.*;

import java.io.IOException;

public class NbpApiInterceptor implements Interceptor {
    private static final MediaType MEDIA_JSON = MediaType.parse("application/json");
    private static final MediaType MEDIA_TEXT = MediaType.parse("text/plain");

    private static final String VALID_CALL_URL = "/api/exchangerates/rates/a/EUR";

    private static final String VALID_EUR_RESPONSE = "{\n" +
            "    \"table\": \"A\",\n" +
            "    \"currency\": \"euro\",\n" +
            "    \"code\": \"EUR\",\n" +
            "    \"rates\": [\n" +
            "        {\n" +
            "            \"no\": \"115/A/NBP/2019\",\n" +
            "            \"effectiveDate\": \"2019-01-02\",\n" +
            "            \"mid\": 1.2345\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private static final String INVALID_RESPONSE = "404 NotFound";

    @Override
    public Response intercept(Chain chain) throws IOException {
        String requestPath = chain.request().url().uri().getPath();

        if (requestPath.equals(VALID_CALL_URL)) {
            return getValidEurResponse(chain);
        } else {
            return getResponseForInvalidCurrencyCode(chain);
        }
    }

    private Response getValidEurResponse(Chain chain) {
        Response response = new Response.Builder()
                .body(ResponseBody.create(MEDIA_JSON, VALID_EUR_RESPONSE))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(200)
                .message("")
                .build();

        return response;
    }

    private Response getResponseForInvalidCurrencyCode(Chain chain) {
        Response response = new Response.Builder()
                .body(ResponseBody.create(MEDIA_TEXT, INVALID_RESPONSE))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(404)
                .message("")
                .build();

        return response;
    }
}

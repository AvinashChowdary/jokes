package com.example.avinashravilla.demo.RestClient;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestClient implements RequestInterceptor {

    private static final String BASE_URL = "https://jokes-api.herokuapp.com";

    private Services services;

    public static RestClient getInstance() {
        return new RestClient();
    }

    /**
     * adapter for retrofit
     * @return
     */
    public Services getServices() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(this)
                .setClient(new retrofit.client.UrlConnectionClient())
                .build();
        services = restAdapter.create(Services.class);
        return services;
    }

    @Override
    public void intercept(RequestFacade request) {
        //
    }

}

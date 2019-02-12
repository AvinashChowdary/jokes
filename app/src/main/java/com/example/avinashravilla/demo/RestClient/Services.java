package com.example.avinashravilla.demo.RestClient;


import com.example.avinashravilla.demo.model.JokeResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * interface with list of services
 * implemented in activities
 */
public interface Services {

    @GET("/api/joke")
    void getJoke(Callback<JokeResponse> callback);

    @GET("/api/joke/{id}")
    void getJokeByID(@Path("id") String id, Callback<JokeResponse> callback);

}

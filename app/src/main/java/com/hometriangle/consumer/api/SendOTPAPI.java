package com.hometriangle.consumer.api;

import com.hometriangle.consumer.service.SendOTPClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hometriangle.consumer.constants.Constants.BASE_URL;

/**
 * Created by sanyam on 7/11/17.
 */

public class SendOTPAPI {

    public static SendOTPClient service = null;

    public static SendOTPClient getService() {
        if(service == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(SendOTPClient.class);
            //Create
        }
        return service;
    }
}

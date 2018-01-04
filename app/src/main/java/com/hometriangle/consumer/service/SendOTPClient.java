package com.hometriangle.consumer.service;

import com.hometriangle.consumer.dto.SendOTPDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.hometriangle.consumer.constants.Constants.SEND_OTP_SLUG;

/**
 * Created by sanyam on 7/11/17.
 */

public interface SendOTPClient {

    @GET(SEND_OTP_SLUG + "{countryCode}/{mobNumber}")
    Call<SendOTPDTO> getOTPData(@Path("countryCode") String cC, @Path("mobNumber") String mN);
}

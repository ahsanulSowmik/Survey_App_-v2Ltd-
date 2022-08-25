package com.example.surveyappv2ltd.retrofit;

import static com.example.surveyappv2ltd.constants.AppConstant.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {


    private static RetrofitRequest instance = null;
    private ApiRequest questionApi;

    private RetrofitRequest() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        questionApi = retrofit.create(ApiRequest.class);
    }

    public static synchronized RetrofitRequest getInstance() {
        if (instance == null) {
            instance = new RetrofitRequest();
        }
        return instance;
    }

    public ApiRequest getQuestionApi() {
        return questionApi;
    }


}

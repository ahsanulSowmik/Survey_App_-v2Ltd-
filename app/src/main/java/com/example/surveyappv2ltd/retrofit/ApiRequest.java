package com.example.surveyappv2ltd.retrofit;

import com.example.surveyappv2ltd.model.Questions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {


    @GET("getSurvey")
    Call<List<Questions>> getQuestions();
}

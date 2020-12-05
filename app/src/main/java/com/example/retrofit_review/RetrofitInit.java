package com.example.retrofit_review;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitInit {
    private static final String BASE_URL = "http://openapi.airkorea.or.kr/";
    Retrofit retrofit = new Retrofit.Builder().
            baseUrl(BASE_URL).
            addConverterFactory(SimpleXmlConverterFactory.create()).
            build();
    OpenApiService openApiService = retrofit.create(OpenApiService.class);
}

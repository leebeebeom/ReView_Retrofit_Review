package com.example.retrofit_review;

import com.example.retrofit_review.AirData.AirData;
import com.example.retrofit_review.AirData.Item;
import com.example.retrofit_review.AirData.Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

//http://openapi.airkorea.or.kr/
// openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?
// serviceKey=70Isc0ZRaL3YQcP4qR2VwEbMQVlQIHayotCjEHOYSTe9Wf9RH0OxU28pZZgzGNAxEpNl5muvcqLS%2ByzF2H8YfQ%3D%3D&
// numOfRows=1000000&
// pageNo=1&
// sidoName=%EC%84%9C%EC%9A%B8&
// ver=1.3

public interface OpenApiService {
    @GET("openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=70Isc0ZRaL3YQcP4qR2VwEbMQVlQIHayotCjEHOYSTe9Wf9RH0OxU28pZZgzGNAxEpNl5muvcqLS%2ByzF2H8YfQ%3D%3D&numOfRows=1000000&pageNo=1&ver=1.3")
    Call<Response> getData(@Query("sidoName") String city, @Query("sort") String sort);
}

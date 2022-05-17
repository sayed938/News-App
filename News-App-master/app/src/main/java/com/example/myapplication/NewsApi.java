package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

@GET("top-headlines?country=eg&apiKey=b15d45a57db54093814bfb61aa1bd7f7")
Call<News> getAllNews();
}



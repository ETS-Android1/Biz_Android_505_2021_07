package com.callor.topnews.service;

import com.callor.topnews.config.NaverAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit을 사용하여 API 조회를 할때 사용할
 * Http Connection 객체를 생성하는 부분
 */
public class NaverRetrofitClient {
    // static type 으로 Connection(instance)를 만드를 method
    private static Retrofit getInstance() {
        return new Retrofit.Builder()
               .baseUrl(NaverAPI.NAVER_BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
    }
    // 생성된 Connection 객체를 사용할 수 있도록 제공하는 method
    public static NaverRetroFit getApiClient() {
        return getInstance().create(NaverRetroFit.class);
    }
}

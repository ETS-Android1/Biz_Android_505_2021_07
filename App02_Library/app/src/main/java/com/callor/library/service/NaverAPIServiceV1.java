package com.callor.library.service;

import android.util.Log;

import com.callor.library.config.Naver;
import com.callor.library.model.NaverParent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NaverAPIServiceV1 extends  Thread{

    private String search ;
    public NaverAPIServiceV1(String search) {
        this.search = search;
    }

    // 3. 생성된 Connection을 통하여 데이터를 가져오고
    // 필요한 데이터를 parsing하여 books 객체에 담기
    public void getNaverBooks(String search) throws Exception {
        Call<NaverParent> books = NaverAPIServiceV1.getNaverBooks()
                .getNaverBook(Naver.CLIENT_ID,Naver.CLIENT_SECRET,this.search);
        Log.d("Naver Books", books.toString());
    }

    // 2. 미리 만들어둔 NaverRetrofitService interface와 연동하여
    // 데이터를 parsing하는 코드를 생성하여 return
    public static NaverRetrofieService getNaverBooks() {
        return getInstance().create(NaverRetrofieService.class);
    }

    // 1. OpenAPI와 연결할 Connection 객체를 생성하고
    public static Retrofit getInstance() {

        // Retrofit으로 OpenAPI로 데이터를 가져오면
        // 자동 parsing을 도와줄 객체
        Gson gson = new GsonBuilder()
                    .setLenient().create();

        // Naver.NAVER_BOOK_URL 과 연결하는
        // HTTP Connection을 하나 설정하고
        // 객체를 만들어라
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Naver.NAVER_BOOK_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        return retrofit;
    }



}

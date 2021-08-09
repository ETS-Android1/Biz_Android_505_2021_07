package com.callor.library.service;

import android.util.Log;

import com.callor.library.config.Naver;
import com.callor.library.model.NaverParent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NaverAPIServiceV1 extends  Thread{

    private String search ;

    // 3. 생성된 Connection을 통하여 데이터를 가져오고
    // 필요한 데이터를 parsing하여 books 객체에 담기
    public void getNaverBooks(String search) {

        // 아래의 코드를 실행하면 Retrofit 설정된 값을 기준으로
        // naver에 요청을 수행한다.
        // 이때 이 코드는 비동기 방식으로 작동이 된다.
        Call<NaverParent> naverCall = NaverAPIServiceV1.getNaverBooks()
                .getNaverBook(
                        Naver.CLIENT_ID,
                        Naver.CLIENT_SECRET,
                        search,
                        10,
                        1);
        /**
         * Retrofit 은 API요청을 비동기 방식으로 수행을 한다
         * 일반적으로 Network이나 외부 다른 장치와 데이터를 주고 받을때는
         * 대부분의 코드를 비동기, 또는 thread 방식으로 사용한다
         *
         * 동기방식
         * 요청수행 ===> 결과가 return 되어 올때까지 대기
         *
         * 비동기방식
         * 요청수행 ===> 결과가 return 되든 말든 다른일 수행하기
         * 결과 return되면 그 결과를 수신하여 처리할 event hanader를
         *      작성해야 한다.
         */
        // Retrofit event 핸들러 작성
        naverCall.enqueue(new Callback<NaverParent>() {

            NaverParent naverParent = null;

            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {

                    // return된  response 객체 확인
                    Log.d("Naver Res Return",response.toString());
                    int resCode = response.code();
                    if(resCode < 300) {
                        naverParent = response.body();
                        Log.d("Naver Return",naverParent.toString());
                    }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {
                Log.d("오류가 발생했음",t.toString());
            }
        });
    }

    // 2. 미리 만들어둔 NaverRetrofitService interface와 연동하여
    // 데이터를 parsing하는 코드를 생성하여 return
    public static NaverRetrofitService getNaverBooks() {
        return getInstance().create(NaverRetrofitService.class);
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
                    .baseUrl(Naver.NAVER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        return retrofit;
    }



}

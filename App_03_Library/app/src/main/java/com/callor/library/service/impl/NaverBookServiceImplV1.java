package com.callor.library.service.impl;

import android.util.Log;

import com.callor.library.config.NaverAPI;
import com.callor.library.model.NaverBookDTO;
import com.callor.library.model.NaverParent;
import com.callor.library.service.NaverBookService;
import com.callor.library.service.RetrofitAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverBookServiceImplV1 implements NaverBookService {

    public NaverBookDTO getBooks(String search) {

        Log.d("Naver Service","Start");
        /**
         * 1. Retrofit을 사용하여 네이버에서 데이터 가져오기
         *      RetrofitAPIClient의 getApiClient() method는
         *      Retrofit interface에 선언된 method를 동반한다
         *      동반된 method( getBook() )를 호출하면서
         *      필요한 매개변수를 전달하면
         *      Retrofit 는 naver server에 요청을 하고 그 요청을
         *      "비동기 방식"으로 기다리게 된다
         *      
         *  2. 비동기 방식
         *      호출하는 곳과 응답을 처리하는 곳이 다르다
         *      호출한 곳에서는 호출만 하고
         *      즉시, 자신의 다른 일을 수행하는 것을 계속한다
         *      
         *      응답이 오면 그 응답을 처리할 CallBack을 
         *      적절히 처리해 주어야 한다
         *      event 핸들링처리 라고도 한다
         *      
         *  3. 동기 방식
         *      어떤 요청을 호출하고, 결과를 마냥 기다리다가
         *      결과가 오면 변수에 담고
         *      이후에 처리하는 코드가 수행된다
         */
        Call<NaverParent> retrofitReturn = RetrofitAPIClient.getApiClient()
                .getBook(
                        NaverAPI.CLIENT_ID,
                        NaverAPI.CLIENT_SECRET,
                        search,
                        1,
                        10);

        /**
         * Retrofit의 요청을 받은 naver가 데이터를 보내면
         * Retrofit이 응답을 받아서 처리할 event(call back)
         *
         * Call Back Event 핸들링 코드
         * CallBack interface를 익명 클래스로 선언하는 코드
         *      Skeletone Code
         */
        retrofitReturn.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                // 응답을 받았을때 Http Code가 무엇인지 확인하기 위하여
                // Http response code를 가져오기
                int resCode = response.code();
                if(resCode < 300) {
                    Log.d("네이버 응답데이터 : ",
                            response.body().toString());
                } else {
                    Log.d("오류 발생:", response.toString());
                }
            }
            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });
        return null;
    }
}

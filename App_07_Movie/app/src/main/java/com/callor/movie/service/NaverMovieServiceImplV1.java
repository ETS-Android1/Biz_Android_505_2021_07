package com.callor.movie.service;

import android.util.Log;

import com.callor.movie.config.NaverAPI;
import com.callor.movie.model.NaverParent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverApiService {
    @Override
    public void getNaverData(String search) {

        Call<NaverParent> naverCall = RetrofitClient.getApiClient().getMovie(
                NaverAPI.NAVER_CLIENT_ID,
                NaverAPI.NAVER_CLIENT_SECRET,
                search,
                1, 20
        );

        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                Log.d("Response",response.toString());
                int resCode = response.code();

                if(resCode < 300) {
                    NaverParent movieData = response.body();
                    Log.d("네이버에서 받은 데이터",movieData.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });

    }
}

package com.callor.movie.service;

import com.callor.movie.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit 을 사용하여
 * OpenAPI 데이터를 가져올 때
 * 정의된 DTO(VO) 를 참조하여
 * 가져온 데이터를 자동 Mapping하여 사용할수 있도록 준비하는
 * 코드가 자동생성된다
 */
public interface NaverRetrofit {

    @GET("movie.json") // End point
    public Call<NaverParent>
            getMovies(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int display
    );
}

package com.callor.library.service;

import android.util.Log;

import com.callor.library.config.Naver;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Collections;

import lombok.SneakyThrows;

public class NaverAPIServiceV1 extends  Thread{

    private String search ;

    public NaverAPIServiceV1(String search) {
        this.search = search;
    }

    @SneakyThrows
    @Override
    public void run() {
        super.run();
        this.getNaverBooks(this.search);
    }

    public void getNaverBooks(String search) throws Exception {
        if(search == null) {
            search = "자바";
        }
        
        // 검색어 문자열을 UTF-8로 encoding하기
        String encSearch = URLEncoder.encode(search,"UTF-8");

        // Naver Open API에 요청할 queryString 만들기
        String queryString = Naver.NAVER_BOOK_URL;
        queryString += "?query=%s";
        queryString += "&display=%d";
        queryString += "&start=%d";
        queryString = String.format(queryString,encSearch,10,1);

        // Network 코딩
        // 생성한 queryString 이용하여 Naver에 요청하기 위한
        // 시작
        URI apiURI = new URI(queryString);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("X-Naver-Client-Id", Naver.CLIENT_ID);
        requestHeaders.set("X-Naver-Client-Secret", Naver.CLIENT_SECRET);
        requestHeaders.setAccept( Collections.singletonList(MediaType.APPLICATION_JSON ));

        String active = "{'active':true}";
//        HttpEntity<String> requestEntity = new HttpEntity<>("parameter",requestHeaders);
        HttpEntity<String> requestEntity = new HttpEntity<>(active, requestHeaders);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate(); //new HttpComponentsClientHttpRequestFactory());

        ResponseEntity<String> result = null;
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        result = restTemplate.exchange(apiURI, HttpMethod.GET,requestEntity,String.class);
        Log.d("Naver",result.getBody());
    }

}

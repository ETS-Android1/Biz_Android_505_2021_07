package com.callor.library.service;

import com.callor.library.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit 를 사용하여 API 접속을 할때
 * 최종 end point, header값, method type, parameter 등을
 * 설정하는 인터페이스
 */
public interface NaverRetrofit {

    /**
     * method의 return type을 void 형식이 아닌 
     * Call 클래스 type으로 설정
     */
    // 1. 가장 기본 타입, 아무일도 할수 없다
    public Call getBook();

    /**
     * 2. naver openAPI를 사용하기 위해서서는
     * 반드시 header에 Client ID와 Client Secret값을
     * 전달해 주어야 한다
     * header에 Client ID와 Client Secret를 전달하기 위하여
     * 가. method의  매개변수에 해당 속성을 지정해 준다
     * 나. @Header() annotation을 부착한다
     * 다. @Header() annotaion에 Header의 이름을 지정한다
     *      X-Naver-Client-Id=aldjflasdjkfldkasfl 형식으로 요청한다
     *
     * 3. method에 end point와 요청 method type을 지정한다
     *      요청 method type : @GET, @POST, @PUT, @DELETE
     *
     * 4. openAPI를 통해 데이터를 요청할때
     *      값, 변수 등을 보내고 싶을때
     *      queryString을 만드는데
     *      여기에서는 @Query() 를 사용하여 지정한다
     *
     * 5. return type인 Call 클래스에
     *      Data를 mpping할 DTO(VO) 클래스를
     *      Generic 으로 설정해 준다
     */
    @GET("book.json")
    public Call<NaverParent> getBook(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int display
    );

}

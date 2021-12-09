package com.callor.login.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.callor.login.R
import com.callor.login.ui.login.LoginViewModel


/**
 * Java 에서는 모든 클래스는 기본적으로 상속을 받을수 있다
 * Kotlin 에서는 모든 클래스는 기본적으로 상속이 불가능하다
 * open 키워드는 이 클래스를 누군가 상속하여 사용할수 있도록 허용하는 코드
 */
open class AuthFragmentParent : Fragment() {

    /**
     * LoginViewModel(store) 데이터를 공유하여 사용하기
     */
    private val loginViewModel : LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userId = loginViewModel.loginResult.value?.success?.userId
        // userId 가 null( ?: ) 이면 없음 이라고 출력
        // null 이 아니면 userId 값을 출력
        Log.d("Author", userId?: "없음")
        if(userId == null) {
            // navigation_login 메뉴를 클릭한 것처럼
            // fragment 를 다른 fragment 로 redirection 하기
            findNavController().navigate(R.id.navigation_login)
        }
    }

}
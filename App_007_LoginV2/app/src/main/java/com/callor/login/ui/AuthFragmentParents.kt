package com.callor.login.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.callor.login.R
import com.callor.login.data.LoginDataSource
import com.callor.login.data.LoginRepository
import com.callor.login.ui.login.LoginViewModel

open class AuthFragmentParents : Fragment() {

    // private lateinit var loginViewModel: LoginViewModel
    // viewModel 을 공유 모드로 변경
    private val loginViewModel: LoginViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = LoginViewModel(
                LoginRepository(dataSource = LoginDataSource())
            ) as T
        }
    }


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
package com.callor.login.data

import com.callor.login.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login

        // 로그인을 수행한 uesrId 와 password 로 DB 로부터 데이터를 조회하고
        // 그 결과를 result 담아서 return 하는 구조
        // val result = dataSource.login(username, password)

        // 로그인 정보와 관계없이 사용자 ID 와 사용자 이름을 임의로 세팅한 결과를 생성하기

        // 사용자 정보가 담긴 LoggedInUser 클래스로 생성한 객체를 Result.Success 클래스에
        // 생성자로 주입하여 result 객체를 선언 및 생성
        val result = Result.Success( LoggedInUser(userId="callor", displayName = "홍길동") )

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
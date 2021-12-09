package com.callor.login.data.model

/**
 * 로그인을 수행했을때 사용자 ID 와 실제 이름을 보여줄 VO 클래스
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
)
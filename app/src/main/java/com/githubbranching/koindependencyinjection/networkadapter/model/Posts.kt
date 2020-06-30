package com.githubbranching.koindependencyinjection.networkadapter.model

data class Posts(
    private val mId: Int? = 0, private val mUserId: String? = "", private val mTitle: String? = "",
    private val mBody: String? = ""
) {
    val id: Int = mId ?: 0
    val userId: String = mUserId ?: ""
    val title: String = mTitle ?: ""
    val body: String = mBody ?: ""
}
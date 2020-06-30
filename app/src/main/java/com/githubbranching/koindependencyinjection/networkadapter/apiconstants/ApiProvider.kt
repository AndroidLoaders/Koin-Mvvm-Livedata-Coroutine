package com.githubbranching.koindependencyinjection.networkadapter.apiconstants

import com.githubbranching.koindependencyinjection.BuildConfig

object ApiProvider {

    private const val GET_POSTS_LIST = "/posts"
    const val ApiGetPostsList = BuildConfig.HOST_URL + GET_POSTS_LIST
}
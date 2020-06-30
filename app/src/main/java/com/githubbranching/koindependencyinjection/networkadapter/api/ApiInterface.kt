package com.githubbranching.koindependencyinjection.networkadapter.api

import com.githubbranching.koindependencyinjection.networkadapter.apiconstants.ApiProvider
import com.githubbranching.koindependencyinjection.networkadapter.model.Posts
import retrofit2.http.GET

interface ApiInterface {

    @GET(ApiProvider.ApiGetPostsList)
    suspend fun getPostsList(): MutableList<Posts>
}
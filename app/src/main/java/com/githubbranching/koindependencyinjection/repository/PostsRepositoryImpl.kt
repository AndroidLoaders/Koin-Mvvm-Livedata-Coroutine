package com.githubbranching.koindependencyinjection.repository

import com.githubbranching.koindependencyinjection.networkadapter.api.ApiInterface
import com.githubbranching.koindependencyinjection.networkadapter.model.Posts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsRepositoryImpl(private val apiClient: ApiInterface) : PostsRepository {

    override fun getPosts(): Flow<Posts> = flow {
        apiClient.getPostsList().forEach {
            emit(it)
        }
    }
}
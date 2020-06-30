package com.githubbranching.koindependencyinjection.repository

import com.githubbranching.koindependencyinjection.networkadapter.model.Posts
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(): Flow<Posts>
}
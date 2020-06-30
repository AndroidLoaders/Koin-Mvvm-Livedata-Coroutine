package com.githubbranching.koindependencyinjection.ui.base

import com.githubbranching.koindependencyinjection.networkadapter.model.Posts

sealed class PostState {
    class AddPost(val post: Posts) : PostState()
    class Error(val message: String?) : PostState()
}
package com.githubbranching.koindependencyinjection.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.githubbranching.koindependencyinjection.custom.io
import com.githubbranching.koindependencyinjection.custom.ui
import com.githubbranching.koindependencyinjection.repository.PostsRepository
import com.githubbranching.koindependencyinjection.ui.base.BaseViewModel
import com.githubbranching.koindependencyinjection.ui.base.PostState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val dataRepo: PostsRepository) : BaseViewModel() {

    private val viewState = MutableLiveData<PostState>()

    fun getPostsState(): LiveData<PostState> = viewState

    fun getPosts() = viewModelScope.launch {
        try {
            io {
                dataRepo.getPosts().collect {
                    ui { viewState.value = PostState.AddPost(it) }
                }
            }
        } catch (e: Exception) {
            ui { viewState.value = PostState.Error(e.message) }
        }
    }
}
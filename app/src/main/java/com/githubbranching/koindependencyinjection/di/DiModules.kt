package com.githubbranching.koindependencyinjection.di

import com.githubbranching.koindependencyinjection.networkadapter.retrofit.RetrofitClient
import com.githubbranching.koindependencyinjection.repository.PostsRepository
import com.githubbranching.koindependencyinjection.repository.PostsRepositoryImpl
import com.githubbranching.koindependencyinjection.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val module = module {
    single { RetrofitClient.createApiClient(androidContext()) }

    viewModel { MainViewModel(get()) }
    single<PostsRepository> { PostsRepositoryImpl(get()) }
}

val appModules = listOf(module)
package com.githubbranching.koindependencyinjection.networkadapter.retrofit

import android.content.Context
import com.githubbranching.koindependencyinjection.BuildConfig
import com.githubbranching.koindependencyinjection.networkadapter.api.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val TAG: String = RetrofitClient::class.java.simpleName

    private const val REQUEST_TIMEOUT = 10L

    private var apiInterface: ApiInterface? = null
    private var httpClient: OkHttpClient? = null

    fun createApiClient(context: Context): ApiInterface = initClient(context)

    private fun initClient(context: Context): ApiInterface {
        if (httpClient == null) setupOkHttpWithCache(context)

        if (apiInterface == null) {
            apiInterface = Retrofit.Builder().baseUrl(BuildConfig.HOST_URL)
                .client(httpClient!!)
                .addConverterFactory(
                    GsonConverterFactory.create(GsonBuilder().setLenient().create())
                )
                .build()
                .create(ApiInterface::class.java)
        }

        return apiInterface!!
    }

    private fun setupOkHttpWithCache(context: Context) {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cacheDir = File(context.cacheDir, "HttpCache")
        val cache = Cache(cacheDir, cacheSize.toLong())

        val httpBuilder = OkHttpClient().newBuilder()
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .cache(cache)

        initOkHttp(httpBuilder)

        httpClient = httpBuilder.build()
    }

    private fun initOkHttp(httpBuilder: OkHttpClient.Builder) {
        httpBuilder.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(interceptor)
        }
    }
}
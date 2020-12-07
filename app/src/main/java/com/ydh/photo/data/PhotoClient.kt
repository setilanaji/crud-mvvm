package com.ydh.photo.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ydh.photo.data.service.PhotoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhotoClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"


    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val photoService by lazy { retrofit.create(PhotoService::class.java) }

}
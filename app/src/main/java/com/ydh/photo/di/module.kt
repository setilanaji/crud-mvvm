package com.ydh.photo.di

import com.ydh.photo.BuildConfig.DEBUG
import com.ydh.photo.data.repository.PhotoRemoteRepository
import com.ydh.photo.data.repository.PhotoRemoteRepositoryImpl
import com.ydh.photo.data.service.PhotoService
import com.ydh.photo.viewmodel.PhotoViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {

    fun providePhotoService(retrofit: Retrofit): PhotoService {
        return retrofit.create(PhotoService::class.java)
    }
    single { providePhotoService(get()) }

}

val networkModule = module {
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        val baseUrl = "https://jsonplaceholder.typicode.com/"
        provideRetrofit(get(), baseUrl)
    }
}

val repositoryModule = module {

    fun providePhotoRepository(api: PhotoService): PhotoRemoteRepository {
        return PhotoRemoteRepositoryImpl(api)
    }
    single { providePhotoRepository(get()) }

}


val viewModelModule = module {

    viewModel {
        PhotoViewModel(repository = get())
    }

}

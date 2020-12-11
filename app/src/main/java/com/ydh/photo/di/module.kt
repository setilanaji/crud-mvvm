package com.ydh.photo.di

import com.ydh.photo.BuildConfig.DEBUG
import com.ydh.photo.data.persistance.contracts.PhotoPersistenceContract
import com.ydh.photo.data.persistance.mappers.PhotoMapper
import com.ydh.photo.data.persistance.mappers.PhotoMapperImpl
import com.ydh.photo.data.persistance.repositories.PhotoRemoteRepository
import com.ydh.photo.data.persistance.repositories.PhotoRemoteRepositoryImpl
import com.ydh.photo.presentation.infrastructure.api.service.PhotoService
import com.ydh.photo.presentation.infrastructure.persistence.PhotoPersistence
import com.ydh.photo.presentation.ui.viewmodel.PhotoViewModel
import com.ydh.photo.usecase.PhotoUseCase
import com.ydh.photo.usecase.PhotoUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

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

val useCaseModule = module {
    fun providePhotoUseCase(repository: PhotoRemoteRepository): PhotoUseCase{
        return PhotoUseCaseImpl(repository)
    }
    single { providePhotoUseCase(get()) }
}

val repositoryModule = module {

    fun providePhotoRepository(persistence: PhotoPersistenceContract, mapper: PhotoMapper ): PhotoRemoteRepository {
        return PhotoRemoteRepositoryImpl(persistence, mapper)
    }
    single { providePhotoRepository(get(), get()) }

}
val mapperModule = module {

    fun providePhotoMapper(): PhotoMapper {
        return PhotoMapperImpl()
    }
    single { providePhotoMapper() }

}

val persistenceModule = module {

    fun providePhotoPersistence(api: PhotoService): PhotoPersistenceContract {
        return PhotoPersistence(api)
    }
    single { providePhotoPersistence(get()) }

}


val viewModelModule = module {

    viewModel {
        PhotoViewModel(useCase = get())
    }

}

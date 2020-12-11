package com.ydh.photo.presentation.infrastructure.api.service

import com.ydh.photo.data.payload.request.PhotoRequest
import com.ydh.photo.data.payload.response.AddUpdateResponse
import com.ydh.photo.data.payload.response.PhotoResponse
import retrofit2.http.*

interface PhotoService {
    @GET("photos")
    suspend fun getAllPhoto(): List<PhotoResponse>

    @POST("photos")
    suspend fun insertPhoto(
        @Body photoRequest: PhotoRequest
    ): AddUpdateResponse

    @DELETE("photos/{id}")
    suspend fun deletePhoto(
        @Path("id") id: Int
    )

    @PUT("photos/{id}")
    suspend fun updatePhoto(
        @Path("id") id: Int,
        @Body request: PhotoRequest
    ): AddUpdateResponse

}
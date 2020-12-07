package com.ydh.photo.data.service

import com.ydh.photo.data.request.PhotoRequest
import com.ydh.photo.data.response.AddUpdateResponse
import com.ydh.photo.data.response.PhotoResponse
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
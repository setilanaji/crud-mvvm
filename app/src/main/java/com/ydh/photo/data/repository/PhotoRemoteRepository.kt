package com.ydh.photo.data.repository

import com.ydh.photo.data.request.PhotoRequest
import com.ydh.photo.data.response.AddUpdateResponse
import com.ydh.photo.data.response.PhotoResponse

interface PhotoRemoteRepository {

    suspend fun getAllPhoto(): List<PhotoResponse>
    suspend fun insertPhoto(photoRequest: PhotoRequest): AddUpdateResponse
    suspend fun deletePhoto(id: Int)
    suspend fun updatePhoto(id: Int, request: PhotoRequest): AddUpdateResponse
}
package com.ydh.photo.data.persistance.contracts

import com.ydh.photo.data.payload.request.PhotoRequest
import com.ydh.photo.data.payload.response.AddUpdateResponse
import com.ydh.photo.data.payload.response.PhotoResponse

interface PhotoPersistenceContract {
    suspend fun getAllPhoto(): List<PhotoResponse>
    suspend fun insertPhoto(photoRequest: PhotoRequest): AddUpdateResponse
    suspend fun deletePhoto(id: Int)
    suspend fun updatePhoto(id: Int, request: PhotoRequest): AddUpdateResponse

}
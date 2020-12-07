package com.ydh.photo.data.repository

import com.ydh.photo.data.request.PhotoRequest
import com.ydh.photo.data.response.AddUpdateResponse
import com.ydh.photo.data.response.PhotoResponse
import com.ydh.photo.data.service.PhotoService

class PhotoRemoteRepositoryImpl(private val service: PhotoService) : PhotoRemoteRepository {
    override suspend fun getAllPhoto(): List<PhotoResponse> {
       return service.getAllPhoto()
    }

    override suspend fun insertPhoto(photoRequest: PhotoRequest): AddUpdateResponse {
        return service.insertPhoto(photoRequest)
    }

    override suspend fun deletePhoto(id: Int) {
        return service.deletePhoto(id)
    }

    override suspend fun updatePhoto(id: Int, request: PhotoRequest): AddUpdateResponse {
        return service.updatePhoto(id, request)
    }

}
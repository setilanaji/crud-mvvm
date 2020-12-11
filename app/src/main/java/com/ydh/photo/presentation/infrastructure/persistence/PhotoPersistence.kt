package com.ydh.photo.presentation.infrastructure.persistence

import com.ydh.photo.data.payload.request.PhotoRequest
import com.ydh.photo.data.payload.response.AddUpdateResponse
import com.ydh.photo.data.payload.response.PhotoResponse
import com.ydh.photo.presentation.infrastructure.api.service.PhotoService
import com.ydh.photo.data.persistance.contracts.PhotoPersistenceContract

class PhotoPersistence(private val service: PhotoService): PhotoPersistenceContract {
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
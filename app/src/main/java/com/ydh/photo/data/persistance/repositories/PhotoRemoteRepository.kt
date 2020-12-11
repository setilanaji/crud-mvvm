package com.ydh.photo.data.persistance.repositories

import com.ydh.photo.data.payload.request.PhotoRequest
import com.ydh.photo.data.payload.response.AddUpdateResponse
import com.ydh.photo.data.payload.response.PhotoResponse
import com.ydh.photo.domain.PhotoDomain

interface PhotoRemoteRepository {

    suspend fun getAllPhoto(): List<PhotoDomain>
    suspend fun insertPhoto(domain: PhotoDomain): PhotoDomain
    suspend fun deletePhoto(domain: PhotoDomain): Int
    suspend fun updatePhoto(id: Int, domain: PhotoDomain): PhotoDomain
}
package com.ydh.photo.usecase

import com.ydh.photo.domain.PhotoDomain

interface PhotoUseCase {
    suspend fun getAllPhoto(): List<PhotoDomain>
    suspend fun insertPhoto(domain: PhotoDomain): PhotoDomain
    suspend fun deletePhoto(domain: PhotoDomain): Int
    suspend fun updatePhoto(id: Int, domain: PhotoDomain): PhotoDomain
}
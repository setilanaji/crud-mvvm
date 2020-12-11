package com.ydh.photo.usecase

import com.ydh.photo.data.persistance.repositories.PhotoRemoteRepository
import com.ydh.photo.domain.PhotoDomain

class PhotoUseCaseImpl(private val repository: PhotoRemoteRepository): PhotoUseCase {
    override suspend fun getAllPhoto(): List<PhotoDomain> {
        return repository.getAllPhoto()
    }

    override suspend fun insertPhoto(domain: PhotoDomain): PhotoDomain {
        return repository.insertPhoto(domain)
    }

    override suspend fun deletePhoto(domain: PhotoDomain): Int {
        return repository.deletePhoto(domain)
    }

    override suspend fun updatePhoto(id: Int, domain: PhotoDomain): PhotoDomain {
        return repository.updatePhoto(id, domain)
    }
}
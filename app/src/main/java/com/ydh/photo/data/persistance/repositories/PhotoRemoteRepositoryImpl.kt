package com.ydh.photo.data.persistance.repositories

import com.ydh.photo.data.payload.request.PhotoRequest
import com.ydh.photo.data.payload.response.AddUpdateResponse
import com.ydh.photo.data.payload.response.PhotoResponse
import com.ydh.photo.presentation.infrastructure.api.service.PhotoService
import com.ydh.photo.data.persistance.contracts.PhotoPersistenceContract
import com.ydh.photo.data.persistance.mappers.PhotoMapper
import com.ydh.photo.domain.PhotoDomain
import java.security.PrivateKey

class PhotoRemoteRepositoryImpl(
    private val persistence: PhotoPersistenceContract,
    private val mapper: PhotoMapper
    ) : PhotoRemoteRepository {
    override suspend fun getAllPhoto(): List<PhotoDomain> {
       return mapper.toListDomain(persistence.getAllPhoto())
    }

    override suspend fun insertPhoto(domain: PhotoDomain): PhotoDomain {
        return mapper.toDomain(persistence.insertPhoto(mapper.toRequest(domain)))
    }

    override suspend fun deletePhoto(domain: PhotoDomain): Int {
        persistence.deletePhoto(domain.id!!)
        return domain.id
    }

    override suspend fun updatePhoto(id: Int, domain: PhotoDomain): PhotoDomain {
        return mapper.toDomain(persistence.updatePhoto(id, mapper.toRequest(domain)))
    }


}
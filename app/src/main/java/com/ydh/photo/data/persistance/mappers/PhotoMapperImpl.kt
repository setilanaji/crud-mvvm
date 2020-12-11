package com.ydh.photo.data.persistance.mappers

import com.ydh.photo.data.payload.request.PhotoRequest
import com.ydh.photo.data.payload.response.AddUpdateResponse
import com.ydh.photo.data.payload.response.PhotoResponse
import com.ydh.photo.domain.PhotoDomain

class PhotoMapperImpl : PhotoMapper {
    override fun toListDomain(responses: List<PhotoResponse>): List<PhotoDomain> {
        return responses.asSequence().map { toDomain(it) }.toList()
    }

    override fun toDomain(response: PhotoResponse): PhotoDomain {
        return PhotoDomain(response.id, response.albumId, response.title, response.url, response.thumbnailUrl)
    }

    override fun toDomain(response: AddUpdateResponse): PhotoDomain {
        return PhotoDomain(response.id)
    }

    override fun toRequest(domain: PhotoDomain): PhotoRequest {
        return PhotoRequest(domain.albumId, domain.title, domain.url, domain.thumbnailUrl)
    }


}
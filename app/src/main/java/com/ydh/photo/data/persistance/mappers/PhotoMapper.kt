package com.ydh.photo.data.persistance.mappers

import com.ydh.photo.data.payload.request.PhotoRequest
import com.ydh.photo.data.payload.response.AddUpdateResponse
import com.ydh.photo.data.payload.response.PhotoResponse
import com.ydh.photo.domain.PhotoDomain

interface PhotoMapper {
    fun toListDomain(responses: List<PhotoResponse>): List<PhotoDomain>
    fun toDomain(response: PhotoResponse): PhotoDomain
    fun toRequest(domain: PhotoDomain): PhotoRequest
    fun toDomain(response: AddUpdateResponse): PhotoDomain
}
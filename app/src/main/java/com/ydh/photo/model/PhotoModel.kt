package com.ydh.photo.model

import android.os.Parcelable
import com.ydh.photo.data.request.PhotoRequest
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoModel(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
): Parcelable

fun PhotoModel.toRequest() = PhotoRequest(albumId, title, url, thumbnailUrl)

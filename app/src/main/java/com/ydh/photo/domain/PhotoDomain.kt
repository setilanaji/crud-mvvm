package com.ydh.photo.domain

import android.os.Parcelable
import com.ydh.photo.data.payload.request.PhotoRequest
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoDomain(
    val id: Int? = null,
    val albumId: Int = 1,
    val title: String = "",
    val url: String = "",
    val thumbnailUrl: String = ""
): Parcelable

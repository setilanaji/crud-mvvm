package com.ydh.photo.data.payload.request

data class PhotoRequest(
    val albumId: Int = 1,
    val title: String = "",
    val url: String = "",
    val thumbnailUrl: String = ""
)
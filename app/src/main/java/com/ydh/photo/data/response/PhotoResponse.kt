package com.ydh.photo.data.response

import com.google.gson.annotations.SerializedName
import com.ydh.photo.model.PhotoModel

data class PhotoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)

fun PhotoResponse.toModel() = PhotoModel(id, albumId, title, url, thumbnailUrl)
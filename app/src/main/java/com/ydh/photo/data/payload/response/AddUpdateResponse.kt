package com.ydh.photo.data.payload.response

import com.google.gson.annotations.SerializedName

data class AddUpdateResponse(
    @SerializedName("id")
    val id: Int
)
package com.ydh.photo.viewmodel.state

import com.ydh.photo.model.PhotoModel

sealed class GetAllState {
    data class Loading(val message: String = "Loading...") : GetAllState()
    data class Error(val exception: Exception) : GetAllState()
    data class SuccessGetAllPhoto(val list: List<PhotoModel>) : GetAllState()
    data class SuccessDeletePhoto(val message: String) : GetAllState()
}
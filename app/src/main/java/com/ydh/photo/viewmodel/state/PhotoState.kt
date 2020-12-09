package com.ydh.photo.viewmodel.state

import com.ydh.photo.model.PhotoModel

sealed class PhotoState {
    data class Loading(val message: String = "Loading...") : PhotoState()
    data class Error(val exception: Exception) : PhotoState()
    data class SuccessGetAllPhoto(val list: List<PhotoModel>) : PhotoState()
    data class SuccessDeletePhoto(val message: String) : PhotoState()
    data class SuccessAddState(val id: Int) : PhotoState()
    data class SuccessUpdateState(val id: Int) : PhotoState()
}
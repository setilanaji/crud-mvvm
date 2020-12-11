package com.ydh.photo.presentation.ui.state

import com.ydh.photo.domain.PhotoDomain


sealed class PhotoState {
    data class Loading(val message: String = "Loading...") : PhotoState()
    data class Error(val exception: Exception) : PhotoState()
    data class SuccessGetAllPhoto(val list: List<PhotoDomain>) : PhotoState()
    data class SuccessDeletePhoto(val message: String) : PhotoState()
    data class SuccessAddState(val id: Int) : PhotoState()
    data class SuccessUpdateState(val id: Int) : PhotoState()
}
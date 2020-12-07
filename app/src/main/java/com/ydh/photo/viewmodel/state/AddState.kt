package com.ydh.photo.viewmodel.state

sealed class AddState {
    data class Loading(val message: String = "Loading...") : AddState()
    data class Error(val exception: Exception) : AddState()
    data class SuccessAddState(val id: Int) : AddState()
    data class SuccessUpdateState(val id: Int) : AddState()
}
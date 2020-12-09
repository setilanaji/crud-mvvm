package com.ydh.photo.viewmodel

import androidx.lifecycle.*
import com.ydh.photo.viewmodel.state.PhotoState
import com.ydh.photo.data.repository.PhotoRemoteRepository
import com.ydh.photo.data.request.PhotoRequest
import com.ydh.photo.data.response.toModel
import com.ydh.photo.model.PhotoModel
import com.ydh.photo.model.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PhotoViewModel(
    private val repository: PhotoRemoteRepository
): ViewModel() {

    private val mutableState by lazy { MutableLiveData<PhotoState>() }
    val state: LiveData<PhotoState> get() = mutableState



    fun getAllPhoto(){
        mutableState.value = PhotoState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoList = repository.getAllPhoto().asSequence().map { it.toModel() }.toList()
                mutableState.postValue(PhotoState.SuccessGetAllPhoto(photoList))
            }catch (ex: Exception){
                onError(ex)
            }
        }

    }

    fun deletePhoto(id: Int){
        mutableState.value = PhotoState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deletePhoto(id)
                mutableState.postValue(PhotoState.SuccessDeletePhoto("success delete data"))
            }catch (ex: Exception){
                onError(ex)
            }
        }
    }


    fun insertPhoto(photoRequest: PhotoRequest){
        mutableState.value = PhotoState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.insertPhoto(photoRequest)
                mutableState.postValue(PhotoState.SuccessAddState(response.id))
            }catch (ex: Exception){
                onError(ex)
            }
        }
    }

    fun updatePhoto(photoModel: PhotoModel){
        mutableState.value = PhotoState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.updatePhoto(photoModel.id, photoModel.toRequest())
                mutableState.postValue(PhotoState.SuccessUpdateState(response.id))
            }catch (ex: Exception){
                onError(ex)
            }
        }
    }

    private fun onError(exc: Exception) {
        exc.printStackTrace()
        mutableState.postValue(PhotoState.Error(exc))
    }

}

class PhotoViewModelFactory(
    private val repository: PhotoRemoteRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(repository) as T
    }
}
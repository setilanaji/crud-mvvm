package com.ydh.photo.viewmodel

import androidx.lifecycle.*
import com.ydh.photo.viewmodel.state.AddState
import com.ydh.photo.data.repository.PhotoRemoteRepository
import com.ydh.photo.data.request.PhotoRequest
import com.ydh.photo.model.PhotoModel
import com.ydh.photo.model.toRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddViewModel(
    val repository: PhotoRemoteRepository
): ViewModel() {

    private val mutableState by lazy { MutableLiveData<AddState>() }
    val state: LiveData<AddState> get() = mutableState


    fun insertPhoto(photoRequest: PhotoRequest){
        mutableState.value = AddState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.insertPhoto(photoRequest)
                mutableState.postValue(AddState.SuccessAddState(response.id))
            }catch (ex: Exception){
                onError(ex)
            }
        }
    }

    fun updatePhoto(photoModel: PhotoModel){
        mutableState.value = AddState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.updatePhoto(photoModel.id, photoModel.toRequest())
                mutableState.postValue(AddState.SuccessUpdateState(response.id))
            }catch (ex: Exception){
                onError(ex)
            }
        }
    }

    private fun onError(exc: Exception) {
        exc.printStackTrace()
        mutableState.postValue(AddState.Error(exc))
    }

}

class AddViewModelFactory(
    private val repository: PhotoRemoteRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddViewModel(repository) as T
    }
}
package com.ydh.photo.viewmodel

import androidx.lifecycle.*
import com.ydh.photo.viewmodel.state.GetAllState
import com.ydh.photo.data.repository.PhotoRemoteRepository
import com.ydh.photo.data.response.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GetAllViewModel(
    private val repository: PhotoRemoteRepository
): ViewModel() {

    private val mutableState by lazy { MutableLiveData<GetAllState>() }
    val state: LiveData<GetAllState> get() = mutableState



    fun getAllPhoto(){
        mutableState.value = GetAllState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val photoList = repository.getAllPhoto().asSequence().map { it.toModel() }.toList()
                mutableState.postValue(GetAllState.SuccessGetAllPhoto(photoList))
            }catch (ex: Exception){
                onError(ex)
            }
        }

    }

    fun deletePhoto(id: Int){
        mutableState.value = GetAllState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deletePhoto(id)
                mutableState.postValue(GetAllState.SuccessDeletePhoto("success delete data"))
            }catch (ex: Exception){
                onError(ex)
            }
        }
    }

    private fun onError(exc: Exception) {
        exc.printStackTrace()
        mutableState.postValue(GetAllState.Error(exc))
    }

}

class GetAllViewModelFactory(
    private val repository: PhotoRemoteRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GetAllViewModel(repository) as T
    }
}
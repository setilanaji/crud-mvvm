package com.ydh.photo.presentation.ui.viewmodel

import androidx.lifecycle.*
import com.ydh.photo.presentation.ui.state.PhotoState
import com.ydh.photo.domain.PhotoDomain
import com.ydh.photo.usecase.PhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PhotoViewModel(
    private val useCase: PhotoUseCase
) : BaseVIewModel() {

    private val mutableState by lazy { MutableLiveData<PhotoState>() }
    val state: LiveData<PhotoState> get() = mutableState

    companion object {
        fun getFactory(
            useCase: PhotoUseCase
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return PhotoViewModel(useCase) as T
                }

            }
        }
    }


    fun getAllPhoto() {
            callCoroutines {
                val photoList = useCase.getAllPhoto()
                println(photoList)
                mutableState.postValue(PhotoState.SuccessGetAllPhoto(photoList))
            }
        }


    fun deletePhoto(photoDomain: PhotoDomain) {
        callCoroutines {
                useCase.deletePhoto(photoDomain)
                mutableState.postValue(PhotoState.SuccessDeletePhoto("success delete data"))
            }
    }


    fun insertPhoto(photoDomain: PhotoDomain) {
        callCoroutines {
            val domain = useCase.insertPhoto(photoDomain)
            mutableState.postValue(domain.id?.let { PhotoState.SuccessAddState(it) })
        }
    }


    fun updatePhoto(photoDomain: PhotoDomain) {
        callCoroutines {
            val domain = photoDomain.id?.let { useCase.updatePhoto(it, photoDomain) }
            if (domain != null) {
                mutableState.postValue(domain.id?.let { PhotoState.SuccessUpdateState(it) })
            }

        }

    }


    private fun callCoroutines(onSuccess: suspend () -> Unit) {
        mutableState.value = PhotoState.Loading()

        val job by lazy {
            viewModelScope.launch {
                (Dispatchers.IO)
                try {
                    onSuccess()
                } catch (ex: java.lang.Exception) {
                    onError(ex)
                }
            }
        }
        jobs.add(job)
    }

    private fun onError(exc: Exception) {
        exc.printStackTrace()
        mutableState.postValue(PhotoState.Error(exc))
    }

}

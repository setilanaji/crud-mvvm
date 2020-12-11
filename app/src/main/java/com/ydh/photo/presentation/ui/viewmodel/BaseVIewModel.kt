package com.ydh.photo.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseVIewModel : ViewModel() {
    protected val jobs by lazy { mutableListOf<Job>() }

    fun onDestroy(){
        jobs.forEach { if (it.isCompleted) it.cancel() }
    }

}

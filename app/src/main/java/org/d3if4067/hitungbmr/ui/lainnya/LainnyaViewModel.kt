package org.d3if4067.hitungbmr.ui.lainnya

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if4067.hitungbmr.model.Lainnya
import org.d3if4067.hitungbmr.network.ApiStatus
import org.d3if4067.hitungbmr.network.DeskripsiApi

class LainnyaViewModel : ViewModel() {
    private val data = MutableLiveData<List<Lainnya>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(DeskripsiApi.service.getDeskripsi())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("LainnyaViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Lainnya>> = data

    fun getStatus(): LiveData<ApiStatus> = status
}
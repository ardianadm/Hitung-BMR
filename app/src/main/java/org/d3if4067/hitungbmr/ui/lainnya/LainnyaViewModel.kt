package org.d3if4067.hitungbmr.ui.lainnya

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if4067.hitungbmr.model.Lainnya
import org.d3if4067.hitungbmr.network.DeskripsiApi

class LainnyaViewModel : ViewModel() {
    private val data = MutableLiveData<List<Lainnya>>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.postValue(DeskripsiApi.service.getDeskripsi())
            } catch (e: Exception) {
                Log.d("LainnyaViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Lainnya>> = data
}
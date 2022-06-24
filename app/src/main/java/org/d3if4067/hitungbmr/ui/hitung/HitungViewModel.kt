package org.d3if4067.hitungbmr.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4067.hitungbmr.db.BmrDao
import org.d3if4067.hitungbmr.db.BmrEntity
import org.d3if4067.hitungbmr.model.HasilBmr
import org.d3if4067.hitungbmr.model.hitungBmr

class HitungViewModel(private val db: BmrDao) : ViewModel() {
    private val hasilBmr = MutableLiveData<HasilBmr?>()
//    private val data = MutableLiveData<Deskripsi?>()

//    init {
//        retrieveData()
//    }

//    private fun retrieveData() {
//        viewModelScope.launch (Dispatchers.IO) {
//            try {
//                data.postValue(DeskripsiApi.service.getDeskripsi())
//            } catch (e: Exception) {
//                Log.d("HitungViewModel", "Failure: ${e.message}")
//            }
//        }
//    }

    fun hitungBmr (isMale: Boolean, usia: Double, berat: Double, tinggi: Double, aktivitas: String) {
        val dataBmr = BmrEntity(
            isMale = isMale,
            usia = usia,
            berat = berat,
            tinggi = tinggi,
            aktivitas = aktivitas
        )
        hasilBmr.value = dataBmr.hitungBmr()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataBmr)
            }
        }
    }

    fun getHasilBmr(): LiveData<HasilBmr?> = hasilBmr
}
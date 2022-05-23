package org.d3if4067.hitungbmr.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4067.hitungbmr.databinding.FragmentHitungBinding
import org.d3if4067.hitungbmr.db.BmrDao
import org.d3if4067.hitungbmr.db.BmrEntity
import org.d3if4067.hitungbmr.model.HasilBmr

class HitungViewModel(private val db: BmrDao) : ViewModel() {
    private val hasilBmr = MutableLiveData<HasilBmr?>()

    val data = db.getLastBmr()

    fun hitungBmr (isMale: Boolean, usia: Double, berat: Double, tinggi: Double, binding: FragmentHitungBinding) {
        if (isMale) {
            val bmr = 66.5 + (13.7 * berat) + (5 * tinggi) - (6.8 * usia)
            val bmrAktivitas = getBmrAktivitas(bmr, binding)
            val beratIdeal = (tinggi - 100) - ((tinggi - 100) * 0.10)

            hasilBmr.value = HasilBmr(bmr, bmrAktivitas, beratIdeal)

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val dataBmr = BmrEntity(
                        isMale = isMale,
                        usia = usia,
                        berat = berat,
                        tinggi = tinggi
                    )
                    db.insert(dataBmr)
                }
            }
        } else {
            val bmr = 655 + (9.6 * berat) + (1.8 * tinggi) - (4.7 * usia)
            val bmrAktivitas = getBmrAktivitas(bmr, binding)
            val beratIdeal = (tinggi - 100) - ((tinggi - 100) * 0.15)

            hasilBmr.value = HasilBmr(bmr, bmrAktivitas, beratIdeal)

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val dataBmr = BmrEntity(
                        isMale = isMale,
                        usia = usia,
                        berat = berat,
                        tinggi = tinggi
                    )
                    db.insert(dataBmr)
                }
            }
        }
    }

    private fun getBmrAktivitas(bmr: Double, binding: FragmentHitungBinding): Double {
        val spinner = binding.spinner
        val bmrAktivitas = when (spinner.selectedItem) {
            "Hampir tidak pernah berolahraga" -> bmr*1.2
            "Jarang berolahraga" -> bmr*1.3
            else -> bmr*1.4
        }
        return bmrAktivitas
    }

    fun getHasilBmr(): LiveData<HasilBmr?> = hasilBmr
}
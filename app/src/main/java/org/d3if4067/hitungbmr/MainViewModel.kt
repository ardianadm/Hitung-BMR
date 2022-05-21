package org.d3if4067.hitungbmr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if4067.hitungbmr.databinding.ActivityMainBinding
import org.d3if4067.hitungbmr.model.HasilBmr

class MainViewModel : ViewModel() {
    private val hasilBmr = MutableLiveData<HasilBmr?>()

    fun hitungBmr (isMale: Boolean, usia: Double, berat: Double, tinggi: Double, binding: ActivityMainBinding) {
        if (isMale) {
            val bmr = 66.5 + (13.7 * berat) + (5 * tinggi) - (6.8 * usia)
            val bmrAktivitas = getBmrAktivitas(bmr, binding)
            val beratIdeal = (tinggi - 100) - ((tinggi - 100) * 0.10)

            hasilBmr.value = HasilBmr(bmr, bmrAktivitas, beratIdeal)
        } else {
            val bmr = 655 + (9.6 * berat) + (1.8 * tinggi) - (4.7 * usia)
            val bmrAktivitas = getBmrAktivitas(bmr, binding)
            val beratIdeal = (tinggi - 100) - ((tinggi - 100) * 0.15)

            hasilBmr.value = HasilBmr(bmr, bmrAktivitas, beratIdeal)
        }
    }

    private fun getBmrAktivitas(bmr: Double, binding: ActivityMainBinding): Double {
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
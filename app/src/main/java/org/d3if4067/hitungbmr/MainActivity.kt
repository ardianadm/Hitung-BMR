package org.d3if4067.hitungbmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import org.d3if4067.hitungbmr.databinding.ActivityMainBinding
import org.d3if4067.hitungbmr.model.HasilBmr

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            hitungBmr()
        }

        binding.btnReset.setOnClickListener {
            reset()
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.activities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinner.adapter = adapter
        }
    }

    private fun hitungBmr() {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val usia = binding.usiaInp.text.toString()
        if (TextUtils.isEmpty(usia)) {
            Toast.makeText(this, R.string.usia_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val berat = binding.beratBadanInp.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(this, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val tinggi = binding.tinggiBadanInp.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (binding.spinner.selectedItem == "Pilih tipe aktivitas Anda!") {
            Toast.makeText(this, R.string.aktivitas_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val result = hitungBmr(
            selectedId == R.id.priaRadioButton,
            usia.toDouble(),
            berat.toDouble(),
            tinggi.toDouble()
        )

        showResult(result)
    }

    private fun hitungBmr (isMale: Boolean, usia: Double, berat: Double, tinggi: Double): HasilBmr {
        if (isMale) {
            val bmr = 66.5 + (13.7 * berat) + (5 * tinggi) - (6.8 * usia)
            val bmrAktivitas = getBmrAktivitas(bmr)
            val beratIdeal = (tinggi - 100) - ((tinggi - 100) * 0.10)

            return HasilBmr(bmr, bmrAktivitas, beratIdeal)
        } else {
            val bmr = 655 + (9.6 * berat) + (1.8 * tinggi) - (4.7 * usia)
            val bmrAktivitas = getBmrAktivitas(bmr)
            val beratIdeal = (tinggi - 100) - ((tinggi - 100) * 0.15)

            return HasilBmr(bmr, bmrAktivitas, beratIdeal)
        }
    }

    private fun getBmrAktivitas(bmr: Double): Double {
        val spinner = binding.spinner
        val bmrAktivitas = when (spinner.selectedItem) {
            "Hampir tidak pernah berolahraga" -> bmr*1.2
            "Jarang berolahraga" -> bmr*1.3
            else -> bmr*1.4
        }
        return bmrAktivitas
    }

    private fun showResult (result: HasilBmr) {
        binding.bmrTextView.text = getString(R.string.bmr_result, result.bmr)
        binding.kaloriTextView.text = getString(R.string.kalori_aktivitas_result, result.bmrAktivitas)
        binding.beratIdealTextView.text = getString(R.string.berat_ideal_result, result.beratIdeal)
    }

    private fun reset() {
        binding.radioGroup.clearCheck()
        binding.usiaInp.setText("")
        binding.beratBadanInp.setText("")
        binding.tinggiBadanInp.setText("")
        binding.spinner.setSelection(0)
        binding.bmrTextView.setText("")
        binding.kaloriTextView.setText("")
        binding.beratIdealTextView.setText("")
    }
}
package org.d3if4067.hitungbmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import org.d3if4067.hitungbmr.databinding.ActivityMainBinding

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

        // === Untuk Pria: ===
        // BMR = (10 × berat dalam kg) + (Tinggi 6,25 × dalam cm) – (usia 5 × dalam tahun) + 5
        // BMR Pria = 66,5 + (13,7 × berat badan) + (5 × tinggi badan) – (6,8 × usia)
        if (selectedId == R.id.priaRadioButton) {
            val bmrPria = 66.5 + (13.7 * berat.toFloat()) + (5 * tinggi.toFloat()) - (6.8 * usia.toFloat())
            val spinner = binding.spinner
            when (spinner.selectedItem) {
                "Hampir tidak pernah berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrPria)
                    binding.kaloriTextView.text = getString(R.string.kalori_aktivitas_result, bmrPria*1.2)
                }
                "Jarang berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrPria)
                    binding.kaloriTextView.text = getString(R.string.kalori_aktivitas_result, bmrPria*1.3)
                }
                "Sering berolahraga atau beraktivitas fisik berat" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrPria)
                    binding.kaloriTextView.text = getString(R.string.kalori_aktivitas_result, bmrPria*1.4)
                }
            }
        }

        // === Untuk Wanita: ===
        // BMR = (10 × berat dalam kg) + (Tinggi 6,25 × dalam cm) – (usia 5 × tahun) – 161
        // BMR Wanita = 655 + (9,6 × berat badan) + (1,8 × tinggi badan) – (4,7 × usia)
        else if (selectedId == R.id.wanitaRadioButton) {
            val bmrWanita = 655 + (9.6 * berat.toFloat()) + (1.8 * tinggi.toFloat()) - (4.7 * usia.toFloat())
            val spinner = binding.spinner
            when (spinner.selectedItem) {
                "Hampir tidak pernah berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrWanita)
                    binding.kaloriTextView.text = getString(R.string.kalori_aktivitas_result, bmrWanita*1.2)
                }
                "Jarang berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrWanita)
                    binding.kaloriTextView.text = getString(R.string.kalori_aktivitas_result, bmrWanita*1.3)
                }
                "Sering berolahraga atau beraktivitas fisik berat" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrWanita)
                    binding.kaloriTextView.text = getString(R.string.kalori_aktivitas_result, bmrWanita*1.4)
                }
            }
        }
    }

    private fun reset() {
        binding.radioGroup.clearCheck()
        binding.usiaInp.setText("")
        binding.beratBadanInp.setText("")
        binding.tinggiBadanInp.setText("")
        binding.spinner.setSelection(0)
        binding.bmrTextView.setText("")
        binding.kaloriTextView.setText("")
    }
}
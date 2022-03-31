package org.d3if4067.hitungbmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
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

        val usia = binding.usiaInp.text.toString().toFloat()
        val tinggi = binding.tinggiBadanInp.text.toString().toFloat()
        val berat = binding.beratBadanInp.text.toString().toFloat()



        // === Untuk Pria: ===
        // BMR = (10 × berat dalam kg) + (Tinggi 6,25 × dalam cm) – (usia 5 × dalam tahun) + 5
        if (selectedId == R.id.priaRadioButton) {
            val bmrPria = (10 * berat) + (6.25 * tinggi) - (5 * usia) + 5
            val spinner = binding.spinner
            when (spinner.selectedItem) {
                "Hampir tidak pernah berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrPria*1.2)
                }
                "Jarang berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrPria*1.3)
                }
                "Sering berolahraga atau beraktivitas fisik berat" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrPria*1.4)
                }
            }
        }

        // === Untuk Wanita: ===
        // BMR = (10 × berat dalam kg) + (Tinggi 6,25 × dalam cm) – (usia 5 × tahun) – 161
        else if (selectedId == R.id.wanitaRadioButton) {
            val bmrWanita = (10 * berat) + (6.25 * tinggi) - (5 * usia) - 161
            val spinner = binding.spinner
            when (spinner.selectedItem) {
                "Hampir tidak pernah berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrWanita*1.2)
                }
                "Jarang berolahraga" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrWanita*1.3)
                }
                "Sering berolahraga atau beraktivitas fisik berat" -> {
                    binding.bmrTextView.text = getString(R.string.bmr_result, bmrWanita*1.4)
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
    }
}
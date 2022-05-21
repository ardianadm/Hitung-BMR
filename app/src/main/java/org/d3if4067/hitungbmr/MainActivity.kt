package org.d3if4067.hitungbmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if4067.hitungbmr.databinding.ActivityMainBinding
import org.d3if4067.hitungbmr.model.HasilBmr

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            hitungBmr()
        }
        viewModel.getHasilBmr().observe(this, { showResult(it) })

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

        viewModel.hitungBmr(
            selectedId == R.id.priaRadioButton,
            usia.toDouble(),
            berat.toDouble(),
            tinggi.toDouble(),
            binding
        )
    }

    private fun showResult (result: HasilBmr?) {
        if (result == null) return

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
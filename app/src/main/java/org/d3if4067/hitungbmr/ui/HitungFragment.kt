package org.d3if4067.hitungbmr.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if4067.hitungbmr.R
import org.d3if4067.hitungbmr.databinding.FragmentHitungBinding
import org.d3if4067.hitungbmr.model.HasilBmr

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSubmit.setOnClickListener {
            hitungBmr()
        }

        viewModel.getHasilBmr().observe(requireActivity(), {
            showResult(it)
        })

        binding.btnReset.setOnClickListener {
            reset()
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireActivity(),
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
            Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val usia = binding.usiaInp.text.toString()
        if (TextUtils.isEmpty(usia)) {
            Toast.makeText(context, R.string.usia_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val berat = binding.beratBadanInp.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val tinggi = binding.tinggiBadanInp.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (binding.spinner.selectedItem == "Pilih tipe aktivitas Anda!") {
            Toast.makeText(context, R.string.aktivitas_invalid, Toast.LENGTH_LONG).show()
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
package org.d3if4067.hitungbmr.ui.histori

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import org.d3if4067.hitungbmr.databinding.FragmentHistoriBinding

class HistoriFragment : Fragment() {

    private lateinit var binding: FragmentHistoriBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoriBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
}
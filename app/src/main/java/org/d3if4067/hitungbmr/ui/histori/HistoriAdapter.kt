package org.d3if4067.hitungbmr.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if4067.hitungbmr.R
import org.d3if4067.hitungbmr.databinding.ItemHistoriBinding
import org.d3if4067.hitungbmr.db.BmrEntity
import org.d3if4067.hitungbmr.model.hitungBmr
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<BmrEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<BmrEntity>() {
                override fun areItemsTheSame(
                    oldData: BmrEntity, newData: BmrEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: BmrEntity, newData: BmrEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: BmrEntity) = with(binding) {
            val hasilBmr = item.hitungBmr()
            val circleBg = kategoriTextView.background as GradientDrawable

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))

            bmrTextView.text = root.context.getString(R.string.hasil_x,
                hasilBmr.bmr, hasilBmr.bmrAktivitas, hasilBmr.beratIdeal)

            val gender = root.context.getString(
                if (item.isMale) R.string.pria else R.string.wanita)

            dataTextView.text = root.context.getString(R.string.data_x,
                item.berat, item.tinggi, gender)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

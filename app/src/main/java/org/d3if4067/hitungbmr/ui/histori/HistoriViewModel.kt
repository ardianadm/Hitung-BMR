package org.d3if4067.hitungbmr.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if4067.hitungbmr.db.BmrDao

class HistoriViewModel(db: BmrDao) : ViewModel() {
    val data = db.getLastBmr()
}
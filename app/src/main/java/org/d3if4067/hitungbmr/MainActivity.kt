package org.d3if4067.hitungbmr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
//import org.d3if4067.hitungbmr.databinding.ActivityMainBinding
import org.d3if4067.hitungbmr.model.HasilBmr
import org.d3if4067.hitungbmr.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
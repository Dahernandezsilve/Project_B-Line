package com.example.proyect_b_line.viewmodel

import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.math.RoundingMode

class poductCartViewModel: ViewModel() {
    fun Re_format(floatVar: Float): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(floatVar).let { "0.00" }
    }
}


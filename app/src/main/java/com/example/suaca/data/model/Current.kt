package com.example.suaca.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Current(
    val temp_c: Double,
    val temp_f: Double
): Parcelable

package com.example.mobileprinters.domain.models
import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class PrinterBrand(
    @PrimaryKey val name: String,
    val version: String,
    val compatibleModels: String
) : Parcelable
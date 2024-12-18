package com.example.mobileprinters.data.local

import androidx.room.*

@Entity
data class PrinterBrandEntity(
    @PrimaryKey val name: String,
    val version: String,
    val compatibleModels: String
)
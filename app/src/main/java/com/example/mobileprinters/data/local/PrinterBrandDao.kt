package com.example.mobileprinters.data.local

import androidx.room.*

@Dao
interface PrinterBrandDao {
    @Query("SELECT * FROM PrinterBrandEntity")
    suspend fun getAllPrinterBrands(): List<PrinterBrandEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrinterBrands(brands: List<PrinterBrandEntity>)
}
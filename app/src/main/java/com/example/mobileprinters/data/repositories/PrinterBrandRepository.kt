package com.example.mobileprinters.data.repositories

import com.example.mobileprinters.domain.models.PrinterBrand

interface PrinterBrandRepository {
    suspend fun getPrinterBrands(): List<PrinterBrand>
    suspend fun savePrinterBrands(brands: List<PrinterBrand>)
}
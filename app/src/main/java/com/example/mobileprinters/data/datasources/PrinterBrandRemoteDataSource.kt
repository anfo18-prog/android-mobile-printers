package com.example.mobileprinters.data.datasources

import com.example.mobileprinters.data.api.PrinterBrandApiService
import com.example.mobileprinters.domain.models.PrinterBrand

class PrinterBrandRemoteDataSource(private val apiService: PrinterBrandApiService) {
    suspend fun fetchPrinterBrands(): List<PrinterBrand> {
        return apiService.getPrinterBrands().map {
            PrinterBrand(it.name, it.version, it.compatibleModels)
        }
    }
}
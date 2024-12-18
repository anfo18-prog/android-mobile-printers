package com.example.mobileprinters.data.api

import retrofit2.http.GET

data class PrinterBrandDto(val name: String, val version: String, val compatibleModels: String)

interface PrinterBrandApiService {
    @GET("site/get-supported-mobile-printer-brands")
    suspend fun getPrinterBrands(): List<PrinterBrandDto>
}
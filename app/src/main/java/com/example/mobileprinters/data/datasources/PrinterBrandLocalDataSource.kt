package com.example.mobileprinters.data.datasources

import com.example.mobileprinters.data.local.PrinterBrandDao
import com.example.mobileprinters.data.mappers.toDomain
import com.example.mobileprinters.data.mappers.toEntity
import com.example.mobileprinters.domain.models.PrinterBrand

class PrinterBrandLocalDataSource(private val dao: PrinterBrandDao) {
    suspend fun getPrinterBrands(): List<PrinterBrand> {
        val data = dao.getAllPrinterBrands()
        return data.map { it.toDomain() }
    }

    suspend fun savePrinterBrands(brands: List<PrinterBrand>) {
        dao.insertPrinterBrands(brands.map { it.toEntity() })
    }
}
package com.example.mobileprinters.data.repositories

import com.example.mobileprinters.data.datasources.PrinterBrandRemoteDataSource
import com.example.mobileprinters.data.datasources.PrinterBrandLocalDataSource
import com.example.mobileprinters.domain.models.PrinterBrand

class PrinterBrandRepositoryImpl(
    private val remoteDataSource: PrinterBrandRemoteDataSource,
    private val localDataSource: PrinterBrandLocalDataSource
) : PrinterBrandRepository {

    override suspend fun getPrinterBrands(): List<PrinterBrand> {
        val localData = localDataSource.getPrinterBrands()
        return localData.ifEmpty {
            val remoteData = remoteDataSource.fetchPrinterBrands()
            localDataSource.savePrinterBrands(remoteData)
            remoteData
        }
    }

    override suspend fun savePrinterBrands(brands: List<PrinterBrand>) {
        localDataSource.savePrinterBrands(brands)
    }
}
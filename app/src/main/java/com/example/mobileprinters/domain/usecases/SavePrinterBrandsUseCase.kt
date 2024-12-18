package com.example.mobileprinters.domain.usecases

import com.example.mobileprinters.domain.models.PrinterBrand
import com.example.mobileprinters.data.repositories.PrinterBrandRepository

class SavePrinterBrandsUseCase(private val repository: PrinterBrandRepository) {
    suspend operator fun invoke(brands: List<PrinterBrand>) {
        repository.savePrinterBrands(brands)
    }
}
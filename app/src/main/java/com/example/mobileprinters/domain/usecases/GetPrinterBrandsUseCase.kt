package com.example.mobileprinters.domain.usecases

import com.example.mobileprinters.domain.models.PrinterBrand
import com.example.mobileprinters.data.repositories.PrinterBrandRepository

class GetPrinterBrandsUseCase(private val repository: PrinterBrandRepository) {
    suspend operator fun invoke(): List<PrinterBrand> {
        return repository.getPrinterBrands()
    }
}
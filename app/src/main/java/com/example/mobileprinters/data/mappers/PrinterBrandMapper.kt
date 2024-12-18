package com.example.mobileprinters.data.mappers

import com.example.mobileprinters.data.local.PrinterBrandEntity
import com.example.mobileprinters.domain.models.PrinterBrand

fun PrinterBrandEntity.toDomain(): PrinterBrand = PrinterBrand(
    name = this.name,
    version = this.version,
    compatibleModels = this.compatibleModels
)

fun PrinterBrand.toEntity(): PrinterBrandEntity = PrinterBrandEntity(
    name = this.name,
    version = this.version,
    compatibleModels = this.compatibleModels
)
package com.example.mobileprinters.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PrinterBrandEntity::class], version = 1)
abstract class PrinterBrandDatabase : RoomDatabase() {
    abstract fun printerBrandDao(): PrinterBrandDao
}
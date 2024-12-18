package com.example.mobileprinters.di

import android.content.Context
import androidx.room.Room
import com.example.mobileprinters.data.api.PrinterBrandApiService
import com.example.mobileprinters.data.local.PrinterBrandDao
import com.example.mobileprinters.data.local.PrinterBrandDatabase
import com.example.mobileprinters.data.datasources.PrinterBrandLocalDataSource
import com.example.mobileprinters.data.datasources.PrinterBrandRemoteDataSource
import com.example.mobileprinters.data.repositories.PrinterBrandRepository
import com.example.mobileprinters.data.repositories.PrinterBrandRepositoryImpl
import com.example.mobileprinters.domain.usecases.GetPrinterBrandsUseCase
import com.example.mobileprinters.domain.usecases.SavePrinterBrandsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): PrinterBrandApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.mibox.com.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PrinterBrandApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext context: Context): PrinterBrandDatabase {
        return Room.databaseBuilder(
            context,
            PrinterBrandDatabase::class.java,
            "printer_brand_db"
        )/*.fallbackToDestructiveMigration()
        .setQueryCallback({ sqlQuery, bindArgs -> Log.e("SQL Query", "SQL Query: $sqlQuery | Args: $bindArgs") }, Executors.newSingleThreadExecutor())*/
        .build()
    }

    @Provides
    @Singleton
    fun providePrinterBrandDao(database: PrinterBrandDatabase): PrinterBrandDao {
        return database.printerBrandDao()
    }

    @Provides
    @Singleton
    fun providePrinterBrandRemoteDataSource(apiService: PrinterBrandApiService): PrinterBrandRemoteDataSource {
        return PrinterBrandRemoteDataSource(apiService)
    }

   @Provides
   @Singleton
   fun providePrinterBrandLocalDataSource(dao: PrinterBrandDao) : PrinterBrandLocalDataSource {
       return PrinterBrandLocalDataSource(dao)
   }

    @Provides
    @Singleton
    fun providePrinterBrandRepository(
        remoteDataSource: PrinterBrandRemoteDataSource,
        localDataSource: PrinterBrandLocalDataSource
    ): PrinterBrandRepository = PrinterBrandRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideGetPrinterBrandsUseCase(repository: PrinterBrandRepository): GetPrinterBrandsUseCase {
        return GetPrinterBrandsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSavePrinterBrandsUseCase(repository: PrinterBrandRepository): SavePrinterBrandsUseCase {
        return SavePrinterBrandsUseCase(repository)
    }
}
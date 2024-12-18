package com.example.mobileprinters.presentation.viewmodels

import androidx.lifecycle.*
import com.example.mobileprinters.domain.models.PrinterBrand
import com.example.mobileprinters.domain.usecases.GetPrinterBrandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandViewModel @Inject constructor(
    private val getBrandsUseCase: GetPrinterBrandsUseCase
) : ViewModel() {
    private val _brands = MutableLiveData<List<PrinterBrand>>()
    val brands: LiveData<List<PrinterBrand>> get() = _brands

    fun loadBrands() {
        viewModelScope.launch {
            val fetchedData = getBrandsUseCase()
            _brands.value = fetchedData
        }
    }
}
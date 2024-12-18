import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mobileprinters.domain.models.PrinterBrand
import com.example.mobileprinters.domain.usecases.GetPrinterBrandsUseCase
import com.example.mobileprinters.presentation.viewmodels.BrandViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class BrandViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getPrinterBrandsUseCase: GetPrinterBrandsUseCase = mock()
    private lateinit var viewModel: BrandViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = BrandViewModel(getPrinterBrandsUseCase)
    }

    @Test
    fun `should update brands LiveData when loadBrands is called`() = runTest {
        // Given
        val mockBrands = listOf(PrinterBrand("Brand A", "1.0", "Model X"))
        whenever(getPrinterBrandsUseCase()).thenReturn(mockBrands)

        val observer: Observer<List<PrinterBrand>> = mock()
        viewModel.brands.observeForever(observer)

        // When
        viewModel.loadBrands()

        // Then
        verify(observer).onChanged(mockBrands)
    }
}
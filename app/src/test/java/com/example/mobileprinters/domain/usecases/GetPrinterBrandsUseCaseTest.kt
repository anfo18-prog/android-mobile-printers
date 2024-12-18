import com.example.mobileprinters.domain.models.PrinterBrand
import com.example.mobileprinters.domain.usecases.GetPrinterBrandsUseCase
import com.example.mobileprinters.data.repositories.PrinterBrandRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPrinterBrandsUseCaseTest {

    private val mockRepository: PrinterBrandRepository = mock()
    private val getPrinterBrandsUseCase = GetPrinterBrandsUseCase(mockRepository)

    @Test
    fun `should return brands from repository`() = runBlocking {
        // Given
        val expectedBrands = listOf(
            PrinterBrand("Brand A", "1.0", "Model X"),
            PrinterBrand("Brand B", "2.0", "Model Y")
        )
        whenever(mockRepository.getPrinterBrands()).thenReturn(expectedBrands)

        // When
        val result = getPrinterBrandsUseCase()

        // Them
        assertEquals(expectedBrands, result)
    }
}
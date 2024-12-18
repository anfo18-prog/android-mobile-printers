import com.example.mobileprinters.data.datasources.PrinterBrandLocalDataSource
import com.example.mobileprinters.data.datasources.PrinterBrandRemoteDataSource
import com.example.mobileprinters.data.repositories.PrinterBrandRepositoryImpl
import com.example.mobileprinters.domain.models.PrinterBrand
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PrinterBrandRepositoryImplTest {

    private val remoteDataSource: PrinterBrandRemoteDataSource = mock()
    private val localDataSource: PrinterBrandLocalDataSource = mock()
    private val repository = PrinterBrandRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `should fetch data from remote when local data is empty`() = runBlocking {
        // Given
        whenever(localDataSource.getPrinterBrands()).thenReturn(emptyList())
        val remoteBrands = listOf(PrinterBrand("Brand A", "1.0", "Model X"))
        whenever(remoteDataSource.fetchPrinterBrands()).thenReturn(remoteBrands)

        // When
        val result = repository.getPrinterBrands()

        // Then
        verify(remoteDataSource).fetchPrinterBrands()
        verify(localDataSource).savePrinterBrands(remoteBrands)
        assert(result == remoteBrands)
    }
}
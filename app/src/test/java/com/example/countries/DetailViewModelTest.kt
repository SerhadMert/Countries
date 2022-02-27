package com.example.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.countries.data.entity.countrydetail.CountryDetail
import com.example.countries.data.local.FavoritesDao
import com.example.countries.data.local.LocalDataSource
import com.example.countries.data.remote.ApiService
import com.example.countries.data.remote.RemoteDataSource
import com.example.countries.data.repository.Repository
import com.example.countries.ui.detail.DetailViewModel
import com.example.countries.utils.CoroutineRule
import com.example.countries.utils.Resource
import com.example.countries.utils.parseFileAsResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get: Rule
    var coroutineTestRule = CoroutineRule()

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var favoritesDao: FavoritesDao

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var repository: Repository
    private lateinit var detailViewModel: DetailViewModel

    private val data = parseFileAsResponse<CountryDetail>("CountryDetailFakeDataJson")
    private val mockSuccessResponse = Response.success(data)
    private val errorString = "Error"
    private val errorResponseBody = errorString.toResponseBody("application/json".toMediaTypeOrNull())
    private val mockErrorResponse = Response.error<CountryDetail>(404,errorResponseBody)

    private var countriesStateObserver = mock<Observer<Resource<Any>>>()

    @Before
    fun setUp(){
        remoteDataSource = RemoteDataSource(apiService)
        localDataSource = LocalDataSource(favoritesDao)
        repository = Repository(remoteDataSource,localDataSource)
        detailViewModel = DetailViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get country detail from detail viewModel and check is success`() = coroutineTestRule.runBlockingTest{
        whenever(apiService.getCountryDetailByCode(ArgumentMatchers.anyString())).thenReturn(mockSuccessResponse)

        detailViewModel.getCountryDetailByCode("ET").observeForever(countriesStateObserver)

        verify(countriesStateObserver).onChanged(Resource.loading())
        verify(countriesStateObserver).onChanged(Resource.success(data))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get countries from home viewModel and check is unSuccess`() = coroutineTestRule.runBlockingTest{
        whenever(apiService.getCountryDetailByCode(ArgumentMatchers.anyString())).thenReturn(mockErrorResponse)

        detailViewModel.getCountryDetailByCode("ET").observeForever(countriesStateObserver)

        verify(countriesStateObserver).onChanged(Resource.loading())
        verify(countriesStateObserver).onChanged(Resource.error("Error: Network error: 404 - ${mockErrorResponse.errorBody()}" ))
    }
}
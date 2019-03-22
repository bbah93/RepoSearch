package nyc.bbah.reposearch.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import nyc.bbah.reposearch.model.Items
import nyc.bbah.reposearch.model.Org
import nyc.bbah.reposearch.search.SearchRepository
import nyc.bbah.reposearch.search.SearchViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val repository = mock<SearchRepository>()

    val viewModel: SearchViewModel = SearchViewModel().apply {
        searchRepository = repository
    }

    @Test
    fun `should post success to live data`(){
        val orgQuery = "Uber"
        val org = mock<Org>()

        //when we make a test call just return our mock org
        whenever(repository.orgListCall(orgQuery)).thenReturn(Single.just(org))

        viewModel.getOrgs(orgQuery)

        verify(repository).orgListCall(orgQuery)
        val networkResult = viewModel.getOrgs(orgQuery).value
        assert(networkResult is SearchViewModel.NetworkResult<List<Items>>)
        val success = networkResult as SearchViewModel.NetworkResult.Success<List<Items>>
        assert(success.data == org.items)

    }

}
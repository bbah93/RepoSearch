package nyc.bbah.reposearch.adapter

import com.nhaarman.mockitokotlin2.mock
import nyc.bbah.reposearch.model.Items
import nyc.bbah.reposearch.search.ListNavigator
import nyc.bbah.reposearch.search.controller.SearchListAdapter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchListAdapterTest {
    val navigator = mock<ListNavigator>()
    val items: List<Items> = listOf(Items(
        full_name = "uber/testrepo",
        url = "github.com/api/dummy_url",
        html_url = "http://api.com",
        stargazers_count = 500
    ))

    val adapter = SearchListAdapter(items, navigator)
    @Test
    fun `should have as many items as given`() {
        assert(adapter.itemCount == items.size)
    }
}
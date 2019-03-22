package nyc.bbah.reposearch.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import nyc.bbah.reposearch.model.Items
import nyc.bbah.reposearch.network.GithubService

class SearchViewModel : ViewModel() {

    private val orgs: MutableLiveData<NetworkResult<List<Items>>> = MutableLiveData()
    var searchRepository = SearchRepository(GithubService.AiUtil.githubService)
    private var disposable: Disposable ?= null

    //Safe handling of Network Operations
    sealed class NetworkResult<T> {
        class Success<T>(val data: T) : NetworkResult<T>()
        class Error<T>(val throwable: Throwable) : NetworkResult<T>()
    }


    fun getOrgs(searchQuery: String): LiveData<NetworkResult<List<Items>>>{
        if (orgs.value == null){
            loadOrgList(searchQuery)
        }
        return orgs
    }
    //make call for search query,
    // then grab the items in response body
    // and sort the items in a list by stars
    private fun loadOrgList(searchQuery: String) {
       disposable = searchRepository
            .orgListCall(searchQuery)
           .map { it.items }
           .flattenAsObservable { it }
           .sorted { items1, items2 ->
               //sort items by the star count
               items1.stargazers_count - items2.stargazers_count
           }
           .toList()
            .subscribe({ orgList ->
                orgs.postValue(NetworkResult.Success(orgList))
            },{ t: Throwable ->
                orgs.postValue(NetworkResult.Error(t))
            })
    }

    override fun onCleared() {
        super.onCleared()
        //when view model is finished dispose of our Single operation
        disposable?.dispose()
    }
}

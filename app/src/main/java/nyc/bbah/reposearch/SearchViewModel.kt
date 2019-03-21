package nyc.bbah.reposearch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable
import nyc.bbah.reposearch.model.Org
import nyc.bbah.reposearch.network.GithubService
import java.util.*

class SearchViewModel : ViewModel() {
    private val orgs: MutableLiveData<NetworkResults<List<Org>>> = MutableLiveData()
    val searchRepository = SearchRepository(GithubService.AiUtil.githubService)
    lateinit var disposable: Disposable

    //Safe handling of Network Operations
    sealed class NetworkResults<T>{
        class Success<T>(val data: T): NetworkResults<T>()
        class Error<T>(val t: Throwable): NetworkResults<T>()
    }

    fun getOrgs(searchQuery: String?): LiveData<NetworkResults<List<Org>>>{
        if (orgs.value == null){
            loadOrgList(searchQuery)
        }
        return orgs
    }

    private fun loadOrgList(searchQuery: String?): Disposable {
       disposable = searchRepository
            .orgListCall(searchQuery)
            .subscribe({ orgList ->
                orgs.postValue(NetworkResults.Success(orgList))
            },{ t: Throwable ->
                orgs.postValue(NetworkResults.Error(t))
            })
        return disposable
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

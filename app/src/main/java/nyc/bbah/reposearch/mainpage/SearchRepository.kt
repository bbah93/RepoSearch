package nyc.bbah.reposearch

import io.reactivex.Observable
import nyc.bbah.reposearch.model.Org
import nyc.bbah.reposearch.network.GithubService

class SearchRepository(private val githubService: GithubService){

    //call for a list of organizations on Github in RxJava Observable
    fun orgListCall(orgQuery: String?): Observable<List<Org>>{
        return githubService.fetchOrgRepos(orgQuery)
    }

}
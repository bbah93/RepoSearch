package nyc.bbah.reposearch.search

import io.reactivex.Single
import nyc.bbah.reposearch.model.Org
import nyc.bbah.reposearch.network.GithubService

class SearchRepository(private val githubService: GithubService){

    //call for a Org JSON on Github in RxJava Single
    fun orgListCall(orgQuery: String): Single<Org> {
        return githubService.fetchOrgRepos("org:$orgQuery")
    }
}
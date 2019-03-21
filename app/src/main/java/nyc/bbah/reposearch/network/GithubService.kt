package nyc.bbah.reposearch.network

import io.reactivex.Observable
import nyc.bbah.reposearch.model.Org
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("search/users?q=type:org+{organization}")
    fun fetchOrgRepos(@Path("organization") organization: String?): Observable<List<Org>>

    //allows us to use as reference when makig call
    object AiUtil{
        private const val BASE_URL = "https://api.github.com/"
        val githubService: GithubService get() = NetworkClient
            .getClient(BASE_URL)!!
            .create(GithubService::class.java)
    }
}
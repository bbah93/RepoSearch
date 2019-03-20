package nyc.bbah.reposearch.network

import io.reactivex.Observable
import nyc.bbah.reposearch.model.Repos
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("search/users?q=type:org+{oragnization}")
    fun fetchOrgRepos(@Path("type:") type: String?): Observable<List<Repos>>


    object AiUtil{
        private const val BASE_URL = "https://api.github.com/"
        val githubService: GithubService get() = NetworkClient
            .getClient(BASE_URL)!!
            .create(GithubService::class.java)
    }
}
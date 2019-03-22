package nyc.bbah.reposearch.network

import io.reactivex.Single
import nyc.bbah.reposearch.model.Org
import retrofit2.http.*

interface GithubService {

    @GET("search/repositories")
    fun fetchOrgRepos(@Query(value = "q", encoded = true) name: String): Single<Org>

    //allows direct reference when making call
    object AiUtil {
        private const val BASE_URL = "https://api.github.com/"
        val githubService: GithubService get() = NetworkClient
            .getClient(BASE_URL)
            .create(GithubService::class.java)
    }
}
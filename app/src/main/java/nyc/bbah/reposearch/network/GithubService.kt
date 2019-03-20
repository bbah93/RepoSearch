package nyc.bbah.reposearch.network

interface GithubService {



    object AiUtil{
        private const val BASE_URL = "https://api.github.com/"
        val githubService: GithubService get() = NetworkClient
            .getClient(BASE_URL)!!
            .create(GithubService::class.java)
    }
}
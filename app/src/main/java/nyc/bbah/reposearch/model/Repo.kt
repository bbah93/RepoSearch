package nyc.bbah.reposearch.model

data class Repo(val id: Int,
                val avatar_url: String,
                val url: String,
                val html_url: String,
                val score: Double)
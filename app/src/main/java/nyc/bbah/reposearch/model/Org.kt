package nyc.bbah.reposearch.model

data class Org(val login: String,val id: Int,
               val avatar_url: String,
               val url: String,
               val html_url: String,
               val score: Double)
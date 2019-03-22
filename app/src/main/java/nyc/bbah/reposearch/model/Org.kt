package nyc.bbah.reposearch.model

data class Org(val total_count: Int,
               val incomplete_results: Boolean,
               val items: List<Items>)
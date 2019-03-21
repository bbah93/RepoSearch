package nyc.bbah.reposearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import nyc.bbah.reposearch.model.Org

class SearchListAdapter(private val items: List<Org>): RecyclerView.Adapter<SearchListViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchListViewHolder {
        return SearchListViewHolder(LayoutInflater.from(p0.context)
            .inflate(R.layout.repolist_item, p0, false))
    }

    override fun onBindViewHolder(p0: SearchListViewHolder, p1: Int) {
        val org = items[p1]
        p0.onBind(org)
    }
    override fun getItemCount(): Int {
        if (items.size >= 3) return 3
        return items.size
    }

}
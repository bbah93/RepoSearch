package nyc.bbah.reposearch.search.controller

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.repolist_item.view.*
import nyc.bbah.reposearch.search.ListNavigator
import nyc.bbah.reposearch.model.Items

class SearchListViewHolder(itemView: View?, private val listNavigator: ListNavigator): RecyclerView.ViewHolder(itemView!!){

    fun onBind(items: Items) {
        itemView.orgtext.text = items.full_name

        itemView.setOnClickListener {
            listNavigator.openWebView(items.html_url)
        }
    }
}
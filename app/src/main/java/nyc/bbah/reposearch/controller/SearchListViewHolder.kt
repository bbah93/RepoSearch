package nyc.bbah.reposearch

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import nyc.bbah.reposearch.model.Org
import kotlinx.android.synthetic.main.repolist_item.view.*

class SearchListViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){

    fun onBind(org: Org){
        val img_URL = org.avatar_url
        itemView.orgtext?.text = org.login
        Picasso.get()
            .load(img_URL)
            .into(itemView.orgimage)
    }
}
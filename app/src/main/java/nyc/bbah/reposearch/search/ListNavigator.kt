package nyc.bbah.reposearch.search


import android.os.Bundle
import android.support.v4.app.FragmentActivity
import nyc.bbah.reposearch.R
import nyc.bbah.reposearch.detail.WebViewFragment
import nyc.bbah.reposearch.inTransaction

class ListNavigator(private val activity: FragmentActivity) {

    fun openWebView(url: String) {
        activity.supportFragmentManager.inTransaction {
            val webViewFragment = WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString("page_URL", url)
                }
            }

            replace(R.id.fragment_container, webViewFragment)
            addToBackStack("Web View")
        }
    }
}
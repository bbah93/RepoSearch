package nyc.bbah.reposearch.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

import nyc.bbah.reposearch.R


class WebViewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_web_view, container, false)

        val webView = rootView.findViewById<WebView>(R.id.webview)
        val webSettings = webView.settings

        webSettings.javaScriptEnabled
        //grab url from bundle
        val urlData = arguments
        val urlString = urlData!!.getString("page_URL")

        webView.loadUrl(urlString)

        return rootView
    }
}

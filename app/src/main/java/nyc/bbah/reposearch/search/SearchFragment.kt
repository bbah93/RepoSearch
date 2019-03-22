package nyc.bbah.reposearch.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.search_fragment.*
import nyc.bbah.reposearch.R
import nyc.bbah.reposearch.search.controller.SearchListAdapter
import nyc.bbah.reposearch.model.Items


class  SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        startSearchQuery()
    }

    private fun startSearchQuery(){
        search_button.setOnClickListener {
            progress_bar.visibility = View.VISIBLE

            viewModel.getOrgs(search_edittext.text.toString())
                .observe(this, Observer<SearchViewModel.NetworkResult<List<Items>>> { networkResults ->
                    if(networkResults != null){
                        when(networkResults){
                            is SearchViewModel.NetworkResult.Success -> {
                                showData(networkResults.data)
                                Log.d("SUCCESSFUL Call: ", networkResults.data.toString())
                            }
                            is SearchViewModel.NetworkResult.Error -> {
                                showError()
                                Log.d("FAILED Call: ", networkResults.throwable.toString())
                            }
                        }
                    }
                })
        }
    }

    private fun showError() {
        progress_bar.visibility = View.GONE

        Toast.makeText(activity, "No results found :( ", Toast.LENGTH_LONG).show()
    }

    private fun showData(items: List<Items>){
        progress_bar.visibility = View.GONE
        repo_recyclerview.visibility = View.VISIBLE

        val navigator = ListNavigator(this.activity!!)
        val searchListAdapter = SearchListAdapter(items, navigator)
        repo_recyclerview.layoutManager = LinearLayoutManager(activity)
        repo_recyclerview.adapter = searchListAdapter
    }
}



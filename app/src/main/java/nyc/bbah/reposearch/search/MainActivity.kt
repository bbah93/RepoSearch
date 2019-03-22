package nyc.bbah.reposearch.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nyc.bbah.reposearch.R
import nyc.bbah.reposearch.inTransaction

//container activity for fragments
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                replace(R.id.fragment_container, SearchFragment())
            }
        }
    }
}

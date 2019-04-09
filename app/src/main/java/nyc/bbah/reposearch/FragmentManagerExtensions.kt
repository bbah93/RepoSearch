package nyc.bbah.reposearch

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


//inline extension func for fragment transaction
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}



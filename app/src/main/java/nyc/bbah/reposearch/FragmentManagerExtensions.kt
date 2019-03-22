package nyc.bbah.reposearch

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

//inline extended fragment func
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}
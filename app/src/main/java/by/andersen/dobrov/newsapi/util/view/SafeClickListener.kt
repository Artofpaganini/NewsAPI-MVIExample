package by.andersen.dobrov.newsapi.util.view

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private var defaultInterval:Int = 1000,
    private val onSafeClick: (View) -> Unit
): View.OnClickListener {

    private var lastTimeClicked = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval)
            return

        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}
package by.andersen.dobrov.newsapi.util.view

import android.content.Context
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Устанавливает слушатель, который предотвращает несколько нажатии подряд
 */
fun View.setSafeClickListener(
    onSafeClick: (View) -> Unit
) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.hideSoftKeyboard() {
    val inputMethodManager = context
        .getSystemService(Context.INPUT_METHOD_SERVICE)
        as? InputMethodManager
        ?: return
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.changeVisibility(
    isVisible: Boolean
) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

fun View.getFragmentManagerOrNull() = when (val ctx = this.context) {
    is AppCompatActivity -> ctx.supportFragmentManager
    is Fragment -> ctx.childFragmentManager
    else -> null

}

fun View.onViewLayout(
    onViewLayout: (View) -> Unit
) {
    val currentView = this
    currentView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            onViewLayout(currentView)
            currentView.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}
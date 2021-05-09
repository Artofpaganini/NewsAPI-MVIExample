package by.andersen.dobrov.newsapi.util.fragment

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import by.andersen.dobrov.newsapi.util.activity.hideSoftKeyboard
import by.andersen.dobrov.newsapi.util.activity.setupActionBar
import by.andersen.dobrov.newsapi.util.activity.showSoftKeyboard

fun Fragment.setupActionBar(
    @IdRes toolbarId: Int,
    @DrawableRes backIcon: Int? = null,
    action: ActionBar.() -> Unit = {},
): Toolbar? = activity?.setupActionBar(toolbarId, backIcon, action)

fun Fragment.showSoftKeyboard(
    view: View,
) {
    activity?.showSoftKeyboard(view)
}

fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

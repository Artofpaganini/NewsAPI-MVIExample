package by.andersen.dobrov.newsapi.util.fragment

import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import by.andersen.dobrov.newsapi.util.activity.hideSoftKeyboard
import by.andersen.dobrov.newsapi.util.activity.setupActionBar
import by.andersen.dobrov.newsapi.util.activity.showSoftKeyboard
import kz.alfabank.alfabusiness.extension.context.completeFileDownload
import kz.alfabank.alfabusiness.extension.context.makeToast
import java.io.File

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

fun Fragment.makeToast(
    @StringRes messageRes: Int,
    duration: Int = Toast.LENGTH_LONG
) {
    context?.makeToast(getString(messageRes), duration)
}

fun Fragment.makeToast(
    message: String,
    duration: Int = Toast.LENGTH_LONG
) {
    context?.makeToast(message, duration)
}

fun Fragment.completeFileDownload(file: File, description: String?) {
    context?.completeFileDownload(file, description)
}
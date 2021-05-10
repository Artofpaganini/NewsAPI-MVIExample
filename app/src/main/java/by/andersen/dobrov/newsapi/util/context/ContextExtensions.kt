package kz.alfabank.alfabusiness.extension.context

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import kz.alfabank.alfabusiness.core.util.FileUtil
import java.io.File
import java.util.Locale

fun Context.makeToast(
    @StringRes messageRes: Int,
    duration: Int = Toast.LENGTH_LONG
) {
    makeToast(getString(messageRes), duration)
}

fun Context.makeToast(
    message: String,
    duration: Int = Toast.LENGTH_LONG
) {
    Toast.makeText(this, message, duration).show()
}

fun Context.getPixelByDensity(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.getPixelByDensity(dp: Float): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.getScreenWidthOrNull(): Int? {
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        ?: return null
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay?.getMetrics(displayMetrics)
        ?: return null

    return displayMetrics.widthPixels
}

fun Context.completeFileDownload(file: File, description: String?) {
    FileUtil.compliteFileDownload(file, this, description)
}

fun Context.getResources(locale: Locale): Resources {
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)

    return createConfigurationContext(configuration).resources
}
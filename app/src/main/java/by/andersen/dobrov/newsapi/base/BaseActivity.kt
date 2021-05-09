package by.andersen.dobrov.newsapi.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import by.andersen.dobrov.newsapi.R
import by.andersen.dobrov.newsapi.util.activity.setLightStatusBarIcons
import by.andersen.dobrov.newsapi.util.activity.setStatusBarColor

abstract class BaseActivity(
    @LayoutRes contentLayoutId: Int = R.layout.activity_base,
) : AppCompatActivity(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(R.color.ui_primary)
        setLightStatusBarIcons()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        supportFragmentManager.fragments.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }


}
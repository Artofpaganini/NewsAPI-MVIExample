package by.andersen.dobrov.newsapi.presentation

import android.os.Bundle
import by.andersen.dobrov.newsapi.base.BaseActivity
import by.andersen.dobrov.newsapi.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
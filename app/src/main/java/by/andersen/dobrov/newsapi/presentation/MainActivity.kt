package by.andersen.dobrov.newsapi.presentation

import android.os.Bundle
import by.andersen.dobrov.newsapi.base.BaseActivity
import by.andersen.dobrov.newsapi.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
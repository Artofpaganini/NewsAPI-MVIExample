package by.andersen.dobrov.newsapi.base

import android.app.Application
import by.andersen.dobrov.newsapi.di.readySetStartKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        readySetStartKoin(this)
    }
}
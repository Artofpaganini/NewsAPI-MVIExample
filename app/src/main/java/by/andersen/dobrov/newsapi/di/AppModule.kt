package by.andersen.dobrov.newsapi.di

import android.app.Application
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun readySetStartKoin(app: Application) {
    startKoin {
        if (BuildConfig.DEBUG) {
            androidLogger(Level.DEBUG)
        }
        androidContext(app)
        modules(appModules)
    }
}

val appModules = listOf(
    apiModule,
    networkModule,
    coilModule,
    repositoryModule
)


package by.andersen.dobrov.newsapi.di

import by.andersen.dobrov.newsapi.util.CoilImageLoader
import by.andersen.dobrov.newsapi.util.ImageLoader

import org.koin.dsl.module

val coilModule = module {
    factory {
        coil.ImageLoader.Builder(context = get())
    }
    factory<ImageLoader> {
        CoilImageLoader(
            context = get(),
            loader = get<coil.ImageLoader.Builder>().build()
        )
    }
}

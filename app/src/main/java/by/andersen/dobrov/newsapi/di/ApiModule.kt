package by.andersen.dobrov.newsapi.di

import by.andersen.dobrov.newsapi.data.model.NewsApiResponse
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single(createdAtStart = false) { get<Retrofit>().create(NewsApiResponse::class.java) }
}
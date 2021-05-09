package by.andersen.dobrov.newsapi.di

import by.andersen.dobrov.newsapi.data.NewsListRepositoryImpl
import by.andersen.dobrov.newsapi.domain.NewsListRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<NewsListRepository> {
        NewsListRepositoryImpl(
            newsService = get(),
            newsMapper = get(),
            dateFormatter = get()
        )
    }
}
package by.andersen.dobrov.newsapi.di

import by.andersen.dobrov.newsapi.presentation.list.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsViewModelModule = module {
    viewModel {
        NewsViewModel(
            newsListRepository = get()
        )
    }
}
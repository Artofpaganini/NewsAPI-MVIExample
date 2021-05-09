package by.andersen.dobrov.newsapi.presentation.list.dto

import by.andersen.dobrov.newsapi.util.Const

data class RequestDTO(
    val query: String,
    val from: String,
    val sortBy: String,
    val apiKey: String = Const.API_KEY,
)
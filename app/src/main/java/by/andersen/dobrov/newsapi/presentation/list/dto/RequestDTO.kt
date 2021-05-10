package by.andersen.dobrov.newsapi.presentation.list.dto

import by.andersen.dobrov.newsapi.util.Const.API_KEY
import by.andersen.dobrov.newsapi.util.Const.SORT_BY

data class RequestDTO(
    val query: String,
    val from: String,
    val sortBy: String = SORT_BY,
    val apiKey: String = API_KEY,
)
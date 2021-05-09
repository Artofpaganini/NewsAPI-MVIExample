package by.andersen.dobrov.newsapi.util.error

import by.andersen.dobrov.newsapi.util.error.BaseError


interface ErrorMessageFactory {

    fun getErrorMessageOrDefault(error: BaseError, default: String): String
    fun getErrorMessageOrDefault(error: BaseError): String
    fun getErrorMessageOrNull(error: BaseError): String?
}

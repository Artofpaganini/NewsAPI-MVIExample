package by.andersen.dobrov.newsapi.util.error

interface ErrorHandler<ErrorData> {

    fun handle(data: ErrorData)
}

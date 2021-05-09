package by.andersen.dobrov.newsapi.util

sealed class BaseResult<out Data, out Error> {
    data class Success<Data>(val data: Data) : BaseResult<Data, Nothing>()
    data class Failure<Error>(val error: Error) :
        BaseResult<Nothing, Error>() where Error : Throwable
}

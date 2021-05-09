package by.andersen.dobrov.newsapi.util.error

import retrofit2.HttpException

data class HttpError(
    override val code: HttpErrorCode = HttpErrorCode.Default,
    override val underlying: HttpException? = null
) : BaseError(
    code = code,
    underlying = underlying
) {

    override fun shortName() = "HT"
}

enum class HttpErrorCode(override val code: Int) : BaseErrorCode {
    Default(0)
}

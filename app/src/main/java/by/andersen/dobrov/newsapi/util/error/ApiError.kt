package by.andersen.dobrov.newsapi.util.error

import by.andersen.dobrov.newsapi.util.BaseError
import by.andersen.dobrov.newsapi.util.BaseErrorCode

data class ApiError(
    override val code: ApiErrorCode,
    override val underlying: Throwable? = null,
    override val devMessage: String? = null
) : BaseError(
    code = code,
    underlying = underlying,
    devMessage = devMessage
) {

    override fun shortName() = "NS"
}

enum class ApiErrorCode(override val code: Int) : BaseErrorCode {
    LoadNews(0),

}

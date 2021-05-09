package by.andersen.dobrov.newsapi.util.error

import by.andersen.dobrov.newsapi.util.BaseError
import by.andersen.dobrov.newsapi.util.BaseErrorCode

class DomainError(
    override val code: DomainErrorCode,
    override val underlying: BaseError?,
    override val devMessage: String? = null
) : BaseError(code, underlying, devMessage) {

    override fun shortName() = "DM"
}

enum class DomainErrorCode(override val code: Int) : BaseErrorCode {
    LoadNews(0),
}

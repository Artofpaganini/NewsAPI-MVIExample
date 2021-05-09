package by.andersen.dobrov.newsapi.util.error

data class ParseError(
    override val code: ParseErrorCode,
    override val underlying: Exception? = null,
    override val devMessage: String? = null
) : BaseError(
    code = code,
    underlying = underlying,
    devMessage = devMessage
) {

    override fun shortName() = "PS"
}

enum class ParseErrorCode(override val code: Int) : BaseErrorCode {
    IllegalState(0),
    InvalidParserConfig(0),
    InvalidJson(0)
}

package by.andersen.dobrov.newsapi.util.error

data class UnexpectedError(
    override val code: UndefinedErrorCode = UndefinedErrorCode.Undefined,
    override val devMessage: String? = null,
    override val underlying: Throwable? = null
) : BaseError(
    code = code,
    devMessage = devMessage,
    underlying = underlying
) {

    override fun shortName() = "UE"
}

enum class UndefinedErrorCode(override val code: Int) : BaseErrorCode {
    Undefined(0)
}

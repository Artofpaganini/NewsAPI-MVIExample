package by.andersen.dobrov.newsapi.util.error

data class UserError(
    override val code: UserErrorCode,
    override val underlying: Throwable? = null,
    override val devMessage: String? = null
) : BaseError(
    code = code,
    underlying = underlying,
    devMessage = devMessage
) {

    constructor(
        underlying: Throwable? = null,
        devMessage: String? = null
    ) : this(getUserErrorCode(underlying), underlying, devMessage)

    override fun shortName() = "US"
}

enum class UserErrorCode(override val code: Int) : BaseErrorCode {
    Unexpected(0),
    Unauthorized(1),
    NoConnection(2),
    Parse(3)
}

private fun getUserErrorCode(
    error: Throwable?
): UserErrorCode {
    if (error !is BaseError) {
        return UserErrorCode.Unexpected
    }
    return when (error) {
        is UnauthorizedError -> UserErrorCode.Unauthorized
        is HttpError -> UserErrorCode.Unexpected
        is DataConnectionError -> UserErrorCode.NoConnection
        is ParseError -> UserErrorCode.Parse
        else -> getUserErrorCode(error.underlying)
    }
}
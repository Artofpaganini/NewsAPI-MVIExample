package by.andersen.dobrov.newsapi.util.error

class UnauthorizedError(
    override val code: UnauthorizedErrorCode = UnauthorizedErrorCode.UNAUTHORIZED,
    override val underlying: Throwable? = null,
    val url: String? = null
) : BaseError(code, underlying, devMessage = "User is not authorized for request: $url") {

    override fun shortName() = "UA"
}

enum class UnauthorizedErrorCode(override val code: Int) : BaseErrorCode {
    UNAUTHORIZED(0)
}
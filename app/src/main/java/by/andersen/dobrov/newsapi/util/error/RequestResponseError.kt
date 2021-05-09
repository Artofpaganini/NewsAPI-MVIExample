package by.andersen.dobrov.newsapi.util.error

class RequestResponseError(
    override val code: RequestResponseErrorCode = RequestResponseErrorCode.EmptyBody,
    val url: String? = null,
    override val underlying: Throwable? = null
) : BaseError(code, devMessage = "Body response is invalid for request $url") {

    override fun shortName() = "RR"
}

enum class RequestResponseErrorCode(override val code: Int) : BaseErrorCode {
    EmptyBody(0)
}

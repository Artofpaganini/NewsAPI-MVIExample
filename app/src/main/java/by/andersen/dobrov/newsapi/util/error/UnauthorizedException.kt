package by.andersen.dobrov.newsapi.util.error

import java.io.IOException

//TODO remove safely this class
// For now use this class only in data layer; In domain and presentation layers use UnauthorizedError
// TODO(https://jira.alfa-bank.kz/browse/SE777-205)
data class UnauthorizedException(
    val url: String,
    override val message: String? = "User is not authorized for request $url"
) : IOException(message)

fun UnauthorizedException.toUnauthorizedError(): UnauthorizedError {
    return UnauthorizedError(
            UnauthorizedErrorCode.UNAUTHORIZED,
            this,
            url
    )
}

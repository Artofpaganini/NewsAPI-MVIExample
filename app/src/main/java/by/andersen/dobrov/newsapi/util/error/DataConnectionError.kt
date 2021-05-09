package by.andersen.dobrov.newsapi.util.error

import java.io.IOException

class DataConnectionError(
    override val code: DataConnectionErrorCode,
    override val underlying: Throwable? = null,
    url: String? = null
) : BaseError(code, underlying,
        devMessage = "Connection failed while requesting $url"
) {
    override fun shortName() = "CE"
}

enum class DataConnectionErrorCode(override val code: Int) : BaseErrorCode {
    NoConnection(0),
    Timeout(1)
}

fun IOException.toDataConnectionError(): DataConnectionError {
    return DataConnectionError(DataConnectionErrorCode.NoConnection, underlying = this)
}
package by.andersen.dobrov.newsapi.util

open class BaseError(
    open val code: BaseErrorCode,
    open val underlying: Throwable? = null,
    open val devMessage: String? = null
) : Exception(devMessage, underlying) {

    open fun domain(): String = this.javaClass.simpleName
    open fun errorCode(): Int = code.code
    open fun errorCodeName(): String = code.toString()
    open fun shortName(): String = ""

    inline fun <reified T : BaseError> getUnderlyingErrorOrNull(): T? {
        var child = underlying as? BaseError
        while (child != null) {
            if (child is T) {
                return child
            }
            child = child.underlying as? BaseError
        }
        return null
    }
}

interface BaseErrorCode {
    val code: Int
}

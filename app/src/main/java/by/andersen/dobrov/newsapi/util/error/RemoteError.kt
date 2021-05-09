package by.andersen.dobrov.newsapi.util.error


class RemoteError(
    override val code: RemoteErrorCode,
    serverErrorCode: String? = null,
    devMessage: String? = null,
    message: String? = null,
    url: String? = null
) : BaseError(
    code,
    devMessage = "serverErrorCode: $serverErrorCode \n" +
        "sererMessage: $message \n" +
        "serverDevMessage: $devMessage \n" +
        "url: $url"
) {

    override fun shortName() = "RM"
}

enum class RemoteErrorCode(override val code: Int) : BaseErrorCode {
    ABE_BAD_REQUEST(0),
    ABE_DB_UNEXPECTED(1),
    ABE_DB_DATA_NOT_FOUND(2),
    UNEXPECTED_ERROR_CODE(3),
}

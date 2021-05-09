package by.andersen.dobrov.newsapi.util.error

interface UserErrorHandler : ErrorHandler<UserErrorData>

data class UserErrorData(
    val userError: UserError,
    val onRetry: () -> Unit = {}
)

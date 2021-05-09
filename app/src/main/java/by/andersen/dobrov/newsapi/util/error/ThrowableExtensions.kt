package by.andersen.dobrov.newsapi.util.error

import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.IOException
import javax.xml.parsers.ParserConfigurationException

fun Throwable.toBaseError(): BaseError = when (this) {
    is UnauthorizedException -> {
        UnauthorizedError(underlying = this)
    }
    is HttpException -> {
        if (code() == 401) {
            UnauthorizedError(underlying = this)
        } else {
            HttpError(underlying = this)
        }
    }
    is IllegalStateException -> {
        ParseError(
            code = ParseErrorCode.IllegalState,
            underlying = this
        )
    }
    is ParserConfigurationException -> {
        ParseError(
            code = ParseErrorCode.InvalidParserConfig,
            underlying = this
        )
    }
    is JsonParseException -> {
        ParseError(
            code = ParseErrorCode.InvalidJson,
            underlying = this
        )
    }
    is IOException -> {
        DataConnectionError(underlying = this, code = DataConnectionErrorCode.NoConnection)
    }
    else -> {
        UnexpectedError(underlying = this)
    }
}
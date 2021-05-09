package by.andersen.dobrov.newsapi.util

enum class DateFormat(val pattern: String) {
    UI_DATE_FORMAT("dd.MM.yyyy"),
    API_DATE_FORMAT("yyyy-MM-dd"),
    ISO_8601_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
}
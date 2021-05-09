package by.andersen.dobrov.newsapi.util

abstract class Mapper<in FROM,out TO> {
    abstract fun map(from:FROM):TO
}
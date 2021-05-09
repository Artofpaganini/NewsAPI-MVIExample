package by.andersen.dobrov.newsapi.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsApiResponse(
    @Expose val status: String,
    @Expose val totalResults: Int,
    @Expose val articles: List<Article>,
) : Parcelable {

    data class Article(
        @Expose val source: Source,
        @Expose val author: String,
        @Expose val title: String,
        @Expose val description: String,
        @Expose val url: String,
        @Expose val urlToImage: String,
        @Expose val publishedAt: String,
        @Expose val content: String,
    ) {

        data class Source(
            @Expose val id: String,
            @Expose val name: String,
        )
    }
}
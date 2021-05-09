package by.andersen.dobrov.newsapi.util

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun load(loadData: LoadData)
}

sealed class LoadData(
    open val imageView: ImageView,
    @DrawableRes
    open val placeholderImageId: Int? = null,
    @DrawableRes
    open val errorImageId: Int? = null
) {
    data class UrlLoadData(
        val url: String,
        override val imageView: ImageView,
        @DrawableRes
        override val placeholderImageId: Int? = null,
        @DrawableRes
        override val errorImageId: Int? = null,
    ) : LoadData(
        imageView, placeholderImageId, errorImageId
    )

    data class LocalLoadData(
        val imageRes: Any,
        override val imageView: ImageView,
        @DrawableRes
        override val placeholderImageId: Int? = null,
        @DrawableRes
        override val errorImageId: Int? = null
    ) : LoadData(
        imageView, placeholderImageId, errorImageId
    )
}
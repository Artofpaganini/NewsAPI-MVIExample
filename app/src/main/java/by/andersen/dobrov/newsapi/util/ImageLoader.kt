package by.andersen.dobrov.newsapi.util

import android.widget.ImageView
import androidx.annotation.DrawableRes

private const val DEFAULT_DURATION_MS = 300

interface ImageLoader {

    fun load(loadData: LoadData)
}

data class LoadData(
    val imgSrc: ImageSrc,
    val imageView: ImageView,
    @DrawableRes
    val placeholderImageId: Int? = null,
    @DrawableRes
    val errorImageId: Int? = null,
    val duration: Int? = DEFAULT_DURATION_MS,
    val listener: Listener? = null
) {
    sealed class ImageSrc {
        // drawable, bitmap, drawableRes
        data class Local(val src: Any) : ImageSrc()
        data class Remote(val url: String) : ImageSrc()
    }

    interface Listener {
        fun onStateUpdate(state: LoadState)

        sealed class LoadState(
            open val loadData: LoadData
        ) {
            data class Started(
                override val loadData: LoadData
            ) : LoadState(loadData)

            data class Canceled(
                override val loadData: LoadData
            ) : LoadState(loadData)

            data class Error(
                override val loadData: LoadData,
                val throwable: Throwable
            ) : LoadState(loadData)

            data class Success(
                override val loadData: LoadData,
            ) : LoadState(loadData)
        }
    }
}
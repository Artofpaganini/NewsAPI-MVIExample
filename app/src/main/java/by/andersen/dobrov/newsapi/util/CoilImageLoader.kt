package by.andersen.dobrov.newsapi.util

import android.content.Context
import coil.request.ImageRequest

class CoilImageLoader(
    private val context: Context,
    private val loader: coil.ImageLoader
) : ImageLoader {

    override fun load(loadData: LoadData) {
        val requestBuilder = ImageRequest
            .Builder(context)
            .data(when (loadData) {
                is LoadData.UrlLoadData -> loadData.url
                is LoadData.LocalLoadData -> loadData.imageRes
            })
            .target(loadData.imageView)
        loadData.errorImageId?.let { requestBuilder.error(it) }
        loadData.placeholderImageId?.let { requestBuilder.placeholder(it) }

        loader.enqueue(requestBuilder.build())
    }
}
package by.andersen.dobrov.newsapi.util

import android.content.Context
import coil.request.ImageRequest
import coil.request.ImageResult

class CoilImageLoader(
    private val context: Context,
    private val loader: coil.ImageLoader
) : ImageLoader {

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun load(loadData: LoadData) {
        val requestBuilder = ImageRequest
            .Builder(context)
            .data(when (val data = loadData.imgSrc) {
                is LoadData.ImageSrc.Local -> data.src
                is LoadData.ImageSrc.Remote -> data.url
            })
            .target(loadData.imageView)
        loadData.errorImageId?.let { requestBuilder.error(it) }
        loadData.placeholderImageId?.let { requestBuilder.placeholder(it) }
        loadData.duration?.let { requestBuilder.crossfade(it) }
        loadData.listener?.let {
            requestBuilder.listener(
                object : ImageRequest.Listener {
                    override fun onStart(
                        request: ImageRequest
                    ) = it.onStateUpdate(LoadData.Listener.LoadState.Started(loadData))

                    override fun onCancel(
                        request: ImageRequest
                    ) = it.onStateUpdate(LoadData.Listener.LoadState.Canceled(loadData))

                    override fun onError(
                        request: ImageRequest,
                        throwable: Throwable
                    ) = it.onStateUpdate(LoadData.Listener.LoadState.Error(loadData, throwable))

                    override fun onSuccess(
                        request: ImageRequest,
                        metadata: ImageResult.Metadata
                    ) = it.onStateUpdate(LoadData.Listener.LoadState.Success(loadData))
                }
            )
        }

        loader.enqueue(requestBuilder.build())
    }
}
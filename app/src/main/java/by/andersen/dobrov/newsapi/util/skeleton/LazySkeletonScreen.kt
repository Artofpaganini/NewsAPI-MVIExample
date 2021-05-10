package kz.alfabank.alfabusiness.core.ui.skeleton

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.SkeletonScreen
import com.ethanhua.skeleton.ViewSkeletonScreen

/**
 * SkeletonScreen который будет показывать shimmer, только когда
 * вызовут метод [show], а не сразу при создании как сейчас заложено
 * в самой библиотеке.
 */
sealed class LazySkeletonScreen : SkeletonScreen {
    protected lateinit var skeletonScreen: SkeletonScreen

    override fun hide() {
        if (this::skeletonScreen.isInitialized) skeletonScreen.hide()
    }

    override fun show() {
        if (this::skeletonScreen.isInitialized) {
            skeletonScreen.show()
            return
        }
        showUninitializedSkeleton()
    }

    protected abstract fun showUninitializedSkeleton()

    data class RecyclerSkeleton(
        private val builder: RecyclerViewSkeletonScreen.Builder
    ) : LazySkeletonScreen() {

        override fun showUninitializedSkeleton() {
            skeletonScreen = builder.show()
        }
    }

    data class ViewSkeleton(
        private val builder: ViewSkeletonScreen.Builder
    ) : LazySkeletonScreen() {

        override fun showUninitializedSkeleton() {
            skeletonScreen = builder.show()
        }
    }
}
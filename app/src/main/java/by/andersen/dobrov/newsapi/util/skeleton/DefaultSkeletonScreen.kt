package kz.alfabank.alfabusiness.core.ui.skeleton

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.SkeletonScreen
import com.ethanhua.skeleton.ViewSkeletonScreen

/**
 * Класс с готовой часто используемой конфигурацией skeleton.
 */
sealed class DefaultSkeletonScreen : SkeletonScreen {

    protected lateinit var skeletonScreen: SkeletonScreen

    override fun hide() {
        skeletonScreen.hide()
    }

    override fun show() {
        skeletonScreen.show()
    }

    /**
     * [action] который вызывается после скрытия skeleton.
     * Актуально если у skeleton есть отложенное скрытие
     * и после него нужно что-то сделать.
     */
    fun hide(action: () -> Unit) {
        (skeletonScreen as? TimedSkeletonScreen)?.let {
            it.hide(action)
            return
        }

        hide()
        action()
    }

    data class RecyclerSkeleton(
        private val builder: RecyclerViewSkeletonScreen.Builder,
        private val isTimedSkeleton: Boolean = true
    ) : DefaultSkeletonScreen() {

        init {
            val lazySkeleton = LazySkeletonScreen.RecyclerSkeleton(builder)
            val noResetSkeleton = NoResetSkeletonScreen(lazySkeleton, isShown = false)
            skeletonScreen =
                if (isTimedSkeleton) TimedSkeletonScreen(noResetSkeleton)
                else noResetSkeleton
        }
    }

    data class ViewSkeleton(
        private val builder: ViewSkeletonScreen.Builder,
        private val isTimedSkeleton: Boolean = true
    ) : DefaultSkeletonScreen() {

        init {
            val lazySkeletonScreen = LazySkeletonScreen.ViewSkeleton(builder)
            val noResetSkeleton = NoResetSkeletonScreen(lazySkeletonScreen, isShown = false)
            skeletonScreen =
                if (isTimedSkeleton) TimedSkeletonScreen(noResetSkeleton)
                else noResetSkeleton
        }
    }
}
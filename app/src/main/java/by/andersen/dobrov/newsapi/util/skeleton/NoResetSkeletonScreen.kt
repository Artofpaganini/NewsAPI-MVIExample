package kz.alfabank.alfabusiness.core.ui.skeleton

import com.ethanhua.skeleton.SkeletonScreen

/**
 * SkeletonScreen который не переустанавливает
 * одинаковые состояния у shimmer.
 * В самой библиотеке такой проверки нет.
 *
 * [isShown] - чтобы отличать создан ли декоратор
 * с помощью [LazySkeletonScreen] (не показывается, пока не вызовут show)
 * или напрямую через builder самой библиотеки (показывается сразу).
 */
class NoResetSkeletonScreen(
    private val regularSkeletonScreen: SkeletonScreen,
    private var isShown: Boolean
) : SkeletonScreen {

    override fun hide() {
        if (!isShown) return

        isShown = false
        regularSkeletonScreen.hide()
    }

    override fun show() {
        if (isShown) return

        isShown = true
        regularSkeletonScreen.show()
    }
}
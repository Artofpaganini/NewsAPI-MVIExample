package kz.alfabank.alfabusiness.core.ui.skeleton

import android.os.Handler
import com.ethanhua.skeleton.SkeletonScreen
import java.lang.ref.WeakReference

private const val DEFAULT_LOADING_DURATION_MS = 800L

/**
 * SkeletonScreen который будет показывать shimmer, пока не истечет
 * заданный [loadingDurationMs], чтобы не было мерцания для слишком
 * быстрых загрузок. Чтобы анимация не обрывалась.
 */
class TimedSkeletonScreen(
    private var regularSkeletonScreen: SkeletonScreen,
    private val loadingDurationMs: Long = DEFAULT_LOADING_DURATION_MS,
    private val handler: Handler = Handler()
) : SkeletonScreen {
    private var loadingStartTimeMs: Long = 0L

    override fun hide() {
        handleLoadingDelay()
    }

    override fun show() {
        loadingStartTimeMs = System.currentTimeMillis()
        regularSkeletonScreen.show()
    }

    /**
     * [action] который выполняется после отложенного скрытия skeleton.
     */
    fun hide(action: () -> Unit) {
        handleLoadingDelay(action)
    }

    private fun handleLoadingDelay(action: () -> Unit = {}) {
        val elapsedTime = System.currentTimeMillis() - loadingStartTimeMs
        val isExpired = elapsedTime >= loadingDurationMs
        if (isExpired) {
            regularSkeletonScreen.hide()
            action()
            return
        }

        val timeLeft = loadingDurationMs - elapsedTime
        val skeletonRef = WeakReference(regularSkeletonScreen)
        delay(timeLeft) {
            skeletonRef.get()?.hide()
            action()
        }
    }

    private fun delay(time: Long, action: () -> Unit) {
        handler.postDelayed({ action() }, time)
    }
}
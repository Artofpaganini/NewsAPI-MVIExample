package kz.alfabank.alfabusiness.core.ui.skeleton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kz.alfabank.alfabusiness.core.ui.R

class SkeletonListItemView
@JvmOverloads constructor(
        context: Context,
        attributes: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attributes, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.list_item_skeleton, this, true)
    }
}
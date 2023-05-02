package ru.easycode.words504.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import ru.easycode.words504.R
import ru.easycode.words504.views.animator.AnimationRepeatListener
import ru.easycode.words504.views.animator.HesitateInterpolator
import ru.easycode.words504.views.animator.ProgressAnimator

class ProgressView : LinearLayout {

    private var textViewLeft: TextView
    private var textViewRight: TextView
    private var frameLeft: FrameLayout
    private var frameRight: FrameLayout
    private var translations: ProgressAnimator = ProgressAnimator.Empty()
    private var textChange: ProgressAnimator = ProgressAnimator.Empty()

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.progress_view, this, true)
        frameLeft = getChildAt(0) as FrameLayout
        frameRight = getChildAt(2) as FrameLayout
        textViewLeft = frameLeft.getChildAt(1) as TextView
        textViewRight = frameRight.getChildAt(1) as TextView

        translations = ProgressAnimator.Translations(frameLeft, frameRight, HesitateInterpolator())
        textChange = ProgressAnimator.TextChange(
            AnimationRepeatListener(textViewLeft, textViewRight),
            LinearInterpolator()
        )
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val screenWidth = context.resources.displayMetrics.widthPixels
        val screenPixelDensity = context.resources.displayMetrics.density
        val inputHeightInDp = (layoutParams.height / screenPixelDensity).toInt()
        val inputWidthInDp = (layoutParams.width / screenPixelDensity).toInt()

        if (inputHeightInDp in HEIGHT_RANGE_TOO_SMALL) {
            layoutParams.height =
                MINIMAL_HEIGHT_IN_DP * screenPixelDensity.toInt()
        }
        if (inputWidthInDp in WIDTH_RANGE_TOO_SMALL) {
            layoutParams.width =
                (screenWidth * 0.6).toInt()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val leftBorder = -frameLeft.measuredWidth.toFloat()
        val rightBorder = measuredWidth.toFloat() - leftBorder
        translations.provide(leftBorder, rightBorder).start()
        textChange.provide(0f, 1f).start()
    }

    companion object {
        private val WIDTH_RANGE_TOO_SMALL = 1..299
        private val HEIGHT_RANGE_TOO_SMALL = 1..31
        private const val MINIMAL_HEIGHT_IN_DP = 32
    }
}

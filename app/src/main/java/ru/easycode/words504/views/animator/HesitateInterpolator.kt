package ru.easycode.words504.views.animator

import android.animation.TimeInterpolator

class HesitateInterpolator : TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        val x = 2.0f * input - 1.0f
        return 0.5f * (x * x * x + 1.0f)
    }
}

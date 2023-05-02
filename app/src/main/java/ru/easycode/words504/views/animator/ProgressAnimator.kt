package ru.easycode.words504.views.animator

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View

interface ProgressAnimator {

    fun provide(startValue: Float, finishValue: Float): ValueAnimator

    abstract class Abstract(
        private val animationInterpolator: TimeInterpolator
    ) : ProgressAnimator {

        override fun provide(startValue: Float, finishValue: Float): ValueAnimator {
            return ValueAnimator.ofFloat(startValue, finishValue).apply {
                interpolator = animationInterpolator
                duration = OSCILLATION_TIME_IN_MS
                repeatCount = ValueAnimator.INFINITE
                repeatMode = ValueAnimator.REVERSE
            }
        }
    }

    class TextChange(
        private val textChangeListener: Animator.AnimatorListener,
        interpolator: TimeInterpolator
    ) : Abstract(interpolator) {
        override fun provide(startValue: Float, finishValue: Float): ValueAnimator =
            super.provide(startValue, finishValue).apply {
                addListener(textChangeListener)
            }
    }

    class Translations(
        private val frameLeft: View,
        private val frameRight: View,
        interpolator: TimeInterpolator
    ) : Abstract(interpolator) {

        override fun provide(startValue: Float, finishValue: Float): ValueAnimator =
            super.provide(startValue, finishValue).apply {
                addUpdateListener {
                    val value = this.animatedValue as Float
                    frameLeft.translationX = value
                    frameRight.translationX = -value
                }
            }
    }

    class Empty : ProgressAnimator {
        override fun provide(startValue: Float, finishValue: Float): ValueAnimator {
            throw java.lang.IllegalStateException("animator is not initialized")
        }
    }

    companion object {
        private const val OSCILLATION_TIME_IN_MS = 1500L
    }
}

package ru.easycode.words504.views.animator

import android.animation.Animator
import android.widget.TextView

abstract class AnimationListener : Animator.AnimatorListener {
    override fun onAnimationCancel(animation: Animator) = Unit
    override fun onAnimationEnd(animation: Animator) = Unit
    override fun onAnimationRepeat(animation: Animator) = Unit
    override fun onAnimationStart(animation: Animator) = Unit
}

class AnimationRepeatListener(
    private val leftTextView: TextView,
    private val rightTextView: TextView
) : AnimationListener() {
    private var index = 0
    private var indexRight = 1
    override fun onAnimationRepeat(animation: Animator) {
        super.onAnimationRepeat(animation)
        leftTextView.text = listOfLetters[index]
        rightTextView.text = listOfLetters[indexRight]
        index++
        indexRight++
        if (index == listOfLetters.size) index = 0
        if (indexRight == listOfLetters.size) indexRight = 0
    }

    companion object {
        private val listOfLetters = listOf("א", "Я", "R", "カ")
    }
}

package ru.skillbranch.gameofthrones.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.splash_screen_layout.*
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.base.BaseFragment

class SplashScreenFragment : BaseFragment<SplashScreenViewModel>() {
    override val layoutRes: Int
        get() = R.layout.splash_screen_layout

    override fun subscribeToViewModel() {
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        val x = resources.displayMetrics.widthPixels / 2f
        val y = resources.displayMetrics.heightPixels / 2f

        val heartList = arrayOf(heart1, heart2, heart3, heart4, heart5)

        for (index in 1..heartList.size) {
            val animView = heartList[index - 1]
            val path = Path().apply {
                addCircle(
                    x,
                    y,
                    250f + 50f * index,
                    if (index % 2 == 0) Path.Direction.CW else Path.Direction.CCW
                )
            }

            val moveAnimator = ObjectAnimator.ofFloat(animView, View.X, View.Y, path).apply {
                duration = 5000L / index
                repeatMode = ValueAnimator.RESTART
                repeatCount = ValueAnimator.INFINITE
                interpolator = LinearInterpolator()
            }

            val scaleY = ObjectAnimator.ofFloat(animView, "scaleY", 0.5f, 1.5f)
            val scaleX = ObjectAnimator.ofFloat(animView, "scaleX", 0.5f, 1.5f)
            scaleY.repeatMode = ValueAnimator.REVERSE
            scaleY.repeatCount = ValueAnimator.INFINITE
            scaleY.duration = 250
            scaleX.repeatMode = ValueAnimator.REVERSE
            scaleX.repeatCount = ValueAnimator.INFINITE
            scaleX.duration = 250

            val rotateAnimator =
                ObjectAnimator.ofFloat(animView, View.ROTATION, 360f).apply {
                    duration = 5000L / index
                    repeatMode = ValueAnimator.RESTART
                    repeatCount = ValueAnimator.INFINITE
                    interpolator = LinearInterpolator()
                }
            AnimatorSet().apply {

                playTogether(scaleX, scaleY)
                start()
            }
            rotateAnimator.start()
            moveAnimator.start()
        }
    }

    companion object {
        fun getInstance(): SplashScreenFragment {
            return SplashScreenFragment()
        }
    }
}
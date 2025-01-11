package com.github.freitzzz.android_gb_boot_loading

import android.media.MediaPlayer
import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.logo_text).apply {
            layoutParams = initialLayoutParams(this.layoutParams as ConstraintLayout.LayoutParams)
        }
    }

    override fun onStart() {
        super.onStart()
        val textView = findViewById<TextView>(R.id.logo_text)
        val player = MediaPlayer.create(textView.context, R.raw.boot)
        val animation = AnimationUtils.loadAnimation(textView.context, R.anim.slider).apply {
            fillAfter = true

            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    player.start()
                }

                override fun onAnimationRepeat(animation: Animation?) {

                }
            })
        }

        textView.startAnimation(animation)
    }
}

private fun initialLayoutParams(base: ConstraintLayout.LayoutParams) =
    ConstraintLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply {
        topMargin = -50
        endToEnd = base.endToEnd
        startToStart = base.startToStart
        topToTop = base.topToTop
    }
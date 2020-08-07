package com.inspirecoding.twittermotionlayout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener
        {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int)
            {
                Log.d("onTransitionCompleted", "onTransitionCompleted")
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, framePosition: Float)
            {
                Log.d("onTransitionChange", "$p1 - $p2 - $framePosition")
                if(framePosition < 0.6f && framePosition > 0.5f)
                {
                    Toast.makeText(this@MainActivity, "Frame position is $framePosition", Toast.LENGTH_LONG).show()
                }
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int)
            {
                Log.d("onTransitionStarted", "$p1 - $p2")
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float)
            {
                Log.d("onTransitionTrigger", "$p1 - $p2 - $p3") }
        })
    }

    override fun onResume()
    {
        super.onResume()
        motionLayout.startLayoutAnimation()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean)
    {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus)
        {
            hideSystemUIAndNavigation(this)
        }
    }

    private fun hideSystemUIAndNavigation(activity: Activity)
    {
        val decorView: View = activity.window.decorView

        decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}
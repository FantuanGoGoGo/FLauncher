package org.fossify.home.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import kotlin.math.min
import org.fossify.home.activities.MainActivity
import org.fossify.home.databinding.MinusOneFragmentBinding

class MinusOneFragment(
    context: Context,
    attributeSet: AttributeSet
) : MyFragment<MinusOneFragmentBinding>(context, attributeSet) {

    private var touchDownX = -1f
    private val moveGestureThreshold = context.resources.getDimensionPixelSize(org.fossify.home.R.dimen.move_gesture_threshold)
    private val screenWidth = resources.displayMetrics.widthPixels

    @SuppressLint("ClickableViewAccessibility")
    override fun setupFragment(activity: MainActivity) {
        this.activity = activity
        binding = MinusOneFragmentBinding.bind(this)

        setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> touchDownX = event.rawX
                MotionEvent.ACTION_MOVE -> {
                    val diffX = event.rawX - touchDownX
                    val newX = min(0f, diffX).coerceAtLeast(-screenWidth.toFloat())
                    x = newX
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val diffX = event.rawX - touchDownX
                    if (diffX < -moveGestureThreshold) {
                        activity.hideMinusOneFragment()
                    } else {
                        activity.showMinusOneFragment()
                    }
                }
            }
            true
        }
    }
}

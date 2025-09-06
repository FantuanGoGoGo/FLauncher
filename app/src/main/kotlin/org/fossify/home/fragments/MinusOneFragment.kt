package org.fossify.home.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import org.fossify.home.activities.MainActivity
import org.fossify.home.databinding.MinusOneFragmentBinding

class MinusOneFragment(
    context: Context,
    attributeSet: AttributeSet
) : MyFragment<MinusOneFragmentBinding>(context, attributeSet) {

    private var touchDownX = -1
    private val moveGestureThreshold = context.resources.getDimensionPixelSize(org.fossify.home.R.dimen.move_gesture_threshold)

    @SuppressLint("ClickableViewAccessibility")
    override fun setupFragment(activity: MainActivity) {
        this.activity = activity
        binding = MinusOneFragmentBinding.bind(this)

        setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> touchDownX = event.x.toInt()
                MotionEvent.ACTION_UP -> {
                    val diffX = event.x - touchDownX
                    if (diffX > moveGestureThreshold) {
                        activity.hideMinusOneFragment()
                    }
                }
            }
            true
        }
    }
}

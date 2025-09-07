package org.fossify.home.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import org.fossify.home.activities.MainActivity
import org.fossify.home.databinding.MinusOneFragmentBinding

class MinusOneFragment(
    context: Context,
    attributeSet: AttributeSet,
) : MyFragment<MinusOneFragmentBinding>(context, attributeSet) {

    private var touchDownX = -1f
    private var startX = 0f
    private var lastRawX = 0f
    private var lastMoveDirection = 0f

    private val moveGestureThreshold =
        context.resources.getDimensionPixelSize(org.fossify.home.R.dimen.move_gesture_threshold)

    @SuppressLint("ClickableViewAccessibility")
    override fun setupFragment(activity: MainActivity) {
        this.activity = activity
        binding = MinusOneFragmentBinding.bind(this)

        setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    touchDownX = event.rawX
                    startX = x
                    lastRawX = touchDownX
                    lastMoveDirection = 0f
                }

                MotionEvent.ACTION_MOVE -> {
                    val diff = event.rawX - touchDownX
                    x = (startX + diff).coerceIn(-width.toFloat(), 0f)
                    lastMoveDirection = event.rawX - lastRawX
                    lastRawX = event.rawX
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val shouldHide = when {
                        lastMoveDirection < -moveGestureThreshold -> true
                        lastMoveDirection > moveGestureThreshold -> false
                        else -> x < -width / 2f
                    }

                    if (shouldHide) {
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


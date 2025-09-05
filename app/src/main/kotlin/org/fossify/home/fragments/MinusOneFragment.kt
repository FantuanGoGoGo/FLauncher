package org.fossify.home.fragments

import android.content.Context
import android.util.AttributeSet
import org.fossify.home.activities.MainActivity
import org.fossify.home.databinding.MinusOneFragmentBinding

class MinusOneFragment(
    context: Context,
    attributeSet: AttributeSet
) : MyFragment<MinusOneFragmentBinding>(context, attributeSet) {
    override fun setupFragment(activity: MainActivity) {
        this.activity = activity
        this.binding = MinusOneFragmentBinding.bind(this)
    }
}


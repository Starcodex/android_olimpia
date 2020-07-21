package com.starcodex.olimpia.ui.stepform.adapter


import androidx.viewpager2.widget.ViewPager2

class ScreenPageChangeCallback(private var screenpageSelected: ScreenPageSelected) : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        screenpageSelected.pageSelected(position)
    }
}
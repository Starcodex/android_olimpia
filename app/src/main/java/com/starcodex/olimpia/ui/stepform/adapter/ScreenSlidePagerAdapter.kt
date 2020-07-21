package com.starcodex.olimpia.ui.stepform.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.starcodex.olimpia.ui.stepform.StepPageModel
import com.starcodex.olimpia.ui.stepform.fragment.*
import com.starcodex.olimpia.ui.stepform.fragment.stepdata.StepDataFragment
import com.starcodex.olimpia.ui.stepform.fragment.stepsave.StepSaveFragment

class ScreenSlidePagerAdapter(fm: AppCompatActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return StepPageModel.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> StepDataFragment()
            1 -> StepPictureFragment()
            2 -> StepGeoFragment()
            3 -> StepStatusFragment()
            4 -> StepSaveFragment()
            else -> StepDataFragment()
        }
    }

    fun getTitle(position: Int): Int {
        return StepPageModel.values()[position].titleResId
    }
}
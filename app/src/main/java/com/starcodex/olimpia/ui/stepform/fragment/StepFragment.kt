package com.starcodex.olimpia.ui.stepform.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.stepform.ActivityPagerControl
import com.starcodex.olimpia.ui.stepform.StepPageModel
import org.xmlpull.v1.XmlPullParser

class StepFragment(var pagerControl: ActivityPagerControl) : Fragment() {

    private val LAYOUT = "LAYOUT"
    private val TITLE = "TITLE"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val layout = arguments?.get(LAYOUT)

        return inflater.inflate(layout as Int, container, false)
    }

    companion object {
        fun newInstance(model: StepPageModel, pagerControl: ActivityPagerControl) = StepFragment(pagerControl).apply {
            arguments = bundleOf(
                LAYOUT to model.layoutResId,
                TITLE to model.titleResId
            )
        }
    }
}
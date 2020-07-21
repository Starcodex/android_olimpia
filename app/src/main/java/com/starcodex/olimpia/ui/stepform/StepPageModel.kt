package com.starcodex.olimpia.ui.stepform

import com.starcodex.olimpia.R

enum class StepPageModel constructor(val titleResId: Int, val layoutResId: Int) {
    FORM(R.string.form, R.layout.fragment_stepdata),
    PICTURE(R.string.picture, R.layout.fragment_steppicture),
    GPS(R.string.gps, R.layout.fragment_stepgeo),
    CONNECTION(R.string.connection, R.layout.fragment_stepstatus),
    SAVE(R.string.save, R.layout.fragment_stepsave)
}
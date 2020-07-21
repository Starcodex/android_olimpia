package com.starcodex.olimpia.ui.stepform.fragment.stepdata

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.starcodex.olimpia.util.FormFieldValidator
import com.starcodex.olimpia.util.ValidationRules

class StepDataViewModel(): ViewModel() {

    val validator = FormFieldValidator()
    fun validateForm(
        editTextName : EditText,
        editTextIdNumber : EditText,
        editTextAddress : EditText,
        editTextCity : EditText,
        editTextCountry : EditText,
        editTextPhone : EditText
    ){

    }

}
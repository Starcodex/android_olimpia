package com.starcodex.olimpia.ui.stepform.fragment.stepdata

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.starcodex.olimpia.R
import com.starcodex.olimpia.ui.stepform.ActivityPagerControl
import com.starcodex.olimpia.util.FieldValidation
import com.starcodex.olimpia.util.FormFieldValidator
import com.starcodex.olimpia.util.ValidationRules
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_stepdata.*
import javax.inject.Inject

class StepDataFragment(): Fragment() {

    @Inject
    lateinit var pagerControl: ActivityPagerControl

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  = inflater.inflate(R.layout.fragment_stepdata, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateForm()
        buttonNext.setOnClickListener {
            pagerControl.updatePersonalData(
                editTextName.text.toString(),
                editTextIdNumber.text.toString(),
                editTextAddress.text.toString(),
                editTextCity.text.toString(),
                editTextCountry.text.toString(),
                editTextPhone.text.toString()
            )
            pagerControl.nextPage()
        }
    }

    fun validateForm(){
        val validator = FormFieldValidator()
        validator.setFieldValidations(
            arrayListOf(
            FieldValidation(editTextName, listOf(ValidationRules.NO_EMPTY)),
            FieldValidation(editTextIdNumber, listOf(ValidationRules.NO_EMPTY)),
            FieldValidation(editTextAddress, listOf(ValidationRules.NO_EMPTY)),
            FieldValidation(editTextCity, listOf(ValidationRules.NO_EMPTY)),
            FieldValidation(editTextCountry, listOf(ValidationRules.NO_EMPTY)),
            FieldValidation(editTextPhone, listOf(ValidationRules.NO_EMPTY))
        )
        )

        validator.valid.observe(viewLifecycleOwner, Observer {
            buttonNext.isEnabled = it
        })

    }

}
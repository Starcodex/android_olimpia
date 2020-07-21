package com.starcodex.olimpia.util

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData


class FormFieldValidator {

    val valid = MutableLiveData<Boolean>()

    private var validationFields : ArrayList<FieldValidation> = ArrayList()
    private var validationTotalFields : ArrayList<FieldValidation> = ArrayList()

    fun setFieldValidations(validations: ArrayList<FieldValidation>){
        validationTotalFields = validations
        validations.forEach { field->
            field.editText.addTextChangedListener {
                if(!validationFields.contains(field)){
                    validationFields.add(field)
                }
                valid.value = formValidation(validationFields)
            }
        }
    }

    fun formValidation(validations: ArrayList<FieldValidation>):Boolean {
        for (b in validations) if (!validation(b.editText,b.rules)) return false
        return validations.size == validationTotalFields.size
    }

    fun validation(editText: EditText, rules: List<ValidationRules>): Boolean{
        var rulesQuantity = rules.size
        clearError(editText)
        rules.forEach {
            if(!isInvalid(editText, it)){
                rulesQuantity--
            }
        }
        return rulesQuantity == 0
    }

    fun isInvalid(editText: EditText, rule: ValidationRules): Boolean {
        return when(rule){
            ValidationRules.NO_EMPTY -> isEmpty(editText,rule)
            ValidationRules.LENGTH -> hasLength(editText, rule)
            ValidationRules.EMAIL -> false
        }
    }

    fun hasLength(editText: EditText, rule: ValidationRules) : Boolean {
        val input = editText.text.toString().trim { it <= ' ' }
        return setError(input.length < Integer.valueOf(rule.param!!),editText, rule)
    }

    fun isEmpty(editText: EditText, rule: ValidationRules) : Boolean {
        val input = editText.text.toString().trim { it <= ' ' }
        return setError(input.length == 0, editText, rule)
    }

    fun setError(error: Boolean, editText: EditText, rule: ValidationRules): Boolean {
        if(error){
            editText.error = when(rule){
                ValidationRules.NO_EMPTY -> "Field "+editText.tag+" is required"
                ValidationRules.LENGTH -> "Required at least "+rule.param+" characters."
                else -> "Undefined error"
            }
        }
        return error
    }

    fun clearError(editText: EditText) {
        editText.error = null
    }
}
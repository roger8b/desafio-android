package br.com.rms.gitconsult.utils.validations.user

import br.com.rms.gitconsult.R
import br.com.rms.gitconsult.utils.validations.EditTextValidationException
import br.com.rms.gitconsult.utils.validations.MultipleValidationExceptions
import br.com.rms.gitconsult.utils.validations.ValidationException
import javax.inject.Inject

class UserValidations @Inject constructor() {

    fun validateLoginData(user: String) {
        val validationExceptions = getLoginValidationException(user)
        throwIfNotEmpty(validationExceptions)
    }

    private fun getLoginValidationException(user: String?): MutableList<ValidationException> {
        val validationExceptions = mutableListOf<ValidationException>()

            if (user.isNullOrBlank()) {
                val validationException = EditTextValidationException(R.id.tfiUser, R.string.error_user_name_is_empty)
                validationExceptions.add(validationException)
            }

        return validationExceptions
    }

    private fun throwIfNotEmpty(validationExceptions: MutableList<ValidationException>) {
        if (validationExceptions.isNotEmpty()) throw MultipleValidationExceptions(validationExceptions)
    }

}
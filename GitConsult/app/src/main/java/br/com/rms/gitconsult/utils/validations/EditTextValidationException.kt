package br.com.rms.gitconsult.utils.validations

class EditTextValidationException(val editTextId: Int, val errorStringRes: Int): ValidationException()
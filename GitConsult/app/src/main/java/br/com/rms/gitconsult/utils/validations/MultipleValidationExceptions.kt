package br.com.rms.gitconsult.utils.validations

class MultipleValidationExceptions(val validationExceptions: MutableList<ValidationException>) : ValidationException()

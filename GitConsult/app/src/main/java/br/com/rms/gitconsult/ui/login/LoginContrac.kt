package br.com.rms.gitconsult.ui.login

import br.com.rms.gitconsult.base.mvp.BaseContract
import br.com.rms.gitconsult.utils.validations.ValidationException

interface LoginContrac : BaseContract {

    interface View : BaseContract.View{
        fun getUserName(): String
        fun loginSuccess()
        fun showLoader()
        fun hideLoader()
        fun showErrorMessage(message: Int)
        fun onValidationException(throwable: ValidationException)

    }

    interface Presenter: BaseContract.Presenter<View>{
        fun login()

    }
}
package br.com.rms.gitconsult.ui.login

import br.com.rms.gitconsult.R
import br.com.rms.gitconsult.base.mvp.BasePresenter
import br.com.rms.gitconsult.data.repository.UserRepository
import br.com.rms.gitconsult.utils.validations.ValidationException
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(
    private val userRepository: UserRepository
) : BasePresenter<LoginContrac.View>(), LoginContrac.Presenter {


    override fun login() {
        val userName = view?.getUserName()
        userName?.let {
            userRepository.validateUserName(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onComplete() {
                        getUserRemoteData()
                    }

                    override fun onSubscribe(d: Disposable) {
                        view?.showLoader()
                    }

                    override fun onError(e: Throwable) {
                        if (e is ValidationException) {
                            view?.onValidationException(e)
                        } else {
                            view?.showErrorMessage(R.string.error_on_login)
                            e.printStackTrace()
                        }
                        view?.hideLoader()


                    }

                })
        }
    }

    fun getUserRemoteData() {
        userRepository.loadUserRemoteData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    view?.loginSuccess()
                    view?.hideLoader()
                }

                override fun onSubscribe(d: Disposable) {
                    view?.showLoader()
                }

                override fun onError(e: Throwable) {
                    if (e is ValidationException) {
                        view?.onValidationException(e)
                    } else {
                        view?.showErrorMessage(R.string.error_on_login)
                        e.printStackTrace()
                    }
                    view?.hideLoader()


                }

            })

    }
}
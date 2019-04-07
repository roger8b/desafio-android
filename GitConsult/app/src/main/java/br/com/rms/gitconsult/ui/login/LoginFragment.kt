package br.com.rms.gitconsult.ui.login

import android.app.Activity
import android.view.View
import br.com.rms.gitconsult.R
import br.com.rms.gitconsult.base.view.BaseFragment
import br.com.rms.gitconsult.utils.extensions.fadeIn
import br.com.rms.gitconsult.utils.extensions.loadDrawable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment<LoginContrac.View,LoginContrac.Presenter>(), LoginContrac.View {

    override fun getViewInstance(): LoginContrac.View = this

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initViews() {

        startScreenAnimation()
    }

    fun startScreenAnimation() {
        val time = 50L
        val disposable = Observable.interval(300,time, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(8)
            .map {
                when (it) {
                    3L -> {
                        ivGitLogo.fadeIn(500)
                        ivGitLogo.loadDrawable(R.drawable.logo_github)
                    }
                    4L ->tflUser.fadeIn(500)
                    6L ->btLogin.fadeIn(500)
                    7L -> loginButton()
                    else ->{}
                }
            }.subscribe()

        compositeDisposable.add(disposable)
    }

    private fun loginButton() {
        btLogin.setOnClickListener {
            presenter.login()

        }
    }

    override fun getUserName(): String {
        return tfiUser.text.toString()
    }

    override fun showLoader() {
        flLoader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        flLoader.visibility = View.GONE
    }

    override fun showErrorMessage(message: Int) {
        showToastLong(message)
    }

    override fun loginSuccess() {
        activity?.apply {
            setResult(Activity.RESULT_OK)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

}
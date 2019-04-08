package br.com.rms.gitconsult.ui.splash

import android.app.Activity
import android.content.Intent
import br.com.rms.gitconsult.R
import br.com.rms.gitconsult.base.view.BaseActivity
import br.com.rms.gitconsult.ui.home.HomeActivity
import br.com.rms.gitconsult.ui.login.LoginActivity

const val REQUEST_CODE_LOGIN = 1000
const val REQUEST_CODE_HOME = 1001

class SplashActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun onInitViews() {
        startLoginActivity()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_LOGIN -> processLoginResult(resultCode)
            REQUEST_CODE_HOME -> processHomeResult(resultCode)
        }
    }

    private fun finishThis() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun processLoginResult(resultCode: Int) {
        when (resultCode) {
            Activity.RESULT_OK -> startHomeActivity()
            Activity.RESULT_CANCELED -> finishThis()
        }
    }

    private fun processHomeResult(resultCode: Int) {
        when (resultCode) {
            Activity.RESULT_CANCELED -> startLoginActivity()
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_HOME)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_LOGIN)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }


}

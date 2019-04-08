package br.com.rms.gitconsult.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import br.com.rms.gitconsult.base.mvp.BaseContract
import br.com.rms.gitconsult.data.local.database.entity.Repository
import br.com.rms.gitconsult.data.local.database.entity.User

interface HomeContract : BaseContract {

    interface View : BaseContract.View, LifecycleOwner {
        fun userData(userList: LiveData<List<User>>)
        fun onMoreStatementsReady(repositories: List<Repository>)
        fun showErrorMessage(errorMessage: Int)
        fun showLoading()
        fun hideLoading()

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadMoreRepositories()
        fun loadRepositories(userName: String)
    }
}
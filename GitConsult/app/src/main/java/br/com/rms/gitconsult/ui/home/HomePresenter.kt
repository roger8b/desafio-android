package br.com.rms.gitconsult.ui.home

import androidx.lifecycle.*
import androidx.lifecycle.OnLifecycleEvent
import br.com.rms.gitconsult.R
import br.com.rms.gitconsult.base.mvp.BasePresenter
import br.com.rms.gitconsult.data.local.database.entity.Repository
import br.com.rms.gitconsult.data.repository.UserRepoRepository
import br.com.rms.gitconsult.data.repository.UserRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val userRepository: UserRepository,
    private val repoRepository: UserRepoRepository
) : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var nextPage: Int = 1
    private var maxPage = Int.MAX_VALUE
    private var loading = false
    private var userName = ""

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadUserData(){
        val userList = userRepository.getUserData()
        view?.userData(userList)
    }

    override fun loadMoreRepositories() {
        if (nextPage < maxPage && !loading) {
            loading = true
        }
        repoRepository.loadRemoteRepoData(userName,nextPage, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MutableList<Repository>>{
                override fun onSuccess(repositoryList: MutableList<Repository>) {
                    updatePageData()
                    if(!repositoryList.isNullOrEmpty()){
                        view?.onMoreStatementsReady(repositoryList)
                    }
                    view?.hideLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    view?.showLoading()
                }

                override fun onError(e: Throwable) {
                    view?.showErrorMessage(R.string.error_on_load_repository_list)
                    view?.hideLoading()
                }
            })
    }

    override fun loadRepositories(userName: String) {
        this.userName = userName
        loadMoreRepositories()
    }

    private fun updatePageData() {
        loading = false
        nextPage++
    }




}
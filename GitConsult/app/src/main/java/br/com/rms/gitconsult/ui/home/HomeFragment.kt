package br.com.rms.gitconsult.ui.home

import android.app.Activity
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.gitconsult.R
import br.com.rms.gitconsult.base.view.BaseFragment
import br.com.rms.gitconsult.data.local.database.entity.Repository
import br.com.rms.gitconsult.data.local.database.entity.User
import br.com.rms.gitconsult.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View {

    private val repositoryAdapter = RepositoryAdapter()

    override fun getViewInstance(): HomeContract.View = this

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initViews() {

        val layouManager = LinearLayoutManager(context)
        rvRepositories.layoutManager = layouManager
        rvRepositories.adapter = repositoryAdapter
        rvRepositories.animation = null

        rvRepositories.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layouManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.loadMoreRepositories()
            }
        })

        initExitButton()

    }

    private fun initExitButton() {
        ivExit.setOnClickListener {
            activity?.apply {
                setResult(Activity.RESULT_CANCELED)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    override fun userData(userList: LiveData<List<User>>) {
        userList.observe(this, Observer<List<User>> { users ->
            if (!users.isNullOrEmpty()) {
                users.first {
                    userDataIsReady(it)
                }
            }
        })
    }

    override fun onMoreStatementsReady(repositories: List<Repository>) {
        repositoryAdapter.addRepository(repositories)
    }

    private fun userDataIsReady(user: User): Boolean {
        tvUserName.text = user.name
        tvEmail.text = user.email
        if (user.email.isNullOrEmpty()) {
            ivEmail.visibility = View.GONE
        }
        tvPublicRepository.text = user.public_repos.toString()
        user.login?.let { presenter.loadRepositories(it) }
        return true

    }

    override fun showLoading() {
        pbLoad.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoad.visibility = View.GONE
    }

    override fun showErrorMessage(errorMessage: Int) {
        showToastLong(errorMessage)
    }
}
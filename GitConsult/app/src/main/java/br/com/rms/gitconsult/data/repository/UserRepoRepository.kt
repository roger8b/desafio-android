package br.com.rms.gitconsult.data.repository

import androidx.lifecycle.LiveData
import br.com.rms.gitconsult.data.local.database.dao.RepoDao
import br.com.rms.gitconsult.data.local.database.entity.Repo
import br.com.rms.gitconsult.data.remote.api.GithubApiService
import br.com.rms.gitconsult.data.remote.model.repo.ApiRepoResponse
import io.reactivex.Completable
import io.reactivex.CompletableSource
import javax.inject.Inject

class UserRepoRepository @Inject constructor(
    private val repoDao: RepoDao,
    private val apiService: GithubApiService
) {

    fun loadRemoteRepoData(page: Int, perPage: Int): Completable {
        return apiService.loadUserRepos(page, perPage).flatMapCompletable {
            saveRepoData(it)
        }
    }

    fun saveRepoData(apiRepoResponse: List<ApiRepoResponse>): CompletableSource? {
        return Completable.fromCallable {
            val repoList = mutableListOf<Repo>()
            apiRepoResponse.forEach {
                val repo = Repo(
                    it.id,
                    it.name,
                    it.private,
                    it.created_at,
                    it.updated_at,
                    it.forks_count,
                    it.stargazers_count,
                    it.watchers_count
                )
                repoList.add(repo)
            }
            repoDao.insertAll(repoList)
        }

    }

    fun getRepoData(): LiveData<List<Repo>> {
        return repoDao.loadRepoList()
    }

}
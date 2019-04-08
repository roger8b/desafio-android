package br.com.rms.gitconsult.data.repository

import androidx.lifecycle.LiveData
import br.com.rms.gitconsult.data.local.database.dao.RepositoryDao
import br.com.rms.gitconsult.data.local.database.entity.Repository
import br.com.rms.gitconsult.data.remote.api.GithubApiService
import br.com.rms.gitconsult.data.remote.model.repo.ApiRepoResponse
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Single
import javax.inject.Inject

class UserRepoRepository @Inject constructor(
    private val repositoryDao: RepositoryDao,
    private val apiService: GithubApiService
) {

    fun loadRemoteRepoData(userName: String,page: Int, perPage: Int) : Single<MutableList<Repository>> {
        return apiService.loadUserRepos(userName,page, perPage).map { apiRepositoryListResponse ->
            val repoList = mutableListOf<Repository>()
            apiRepositoryListResponse.forEach {
                val repo = Repository(
                    it.id,
                    it.name,
                    it.private,
                    it.created_at,
                    it.updated_at,
                    it.forks_count,
                    it.stargazers_count,
                    it.watchers_count,
                    it.description,
                    it.language
                )
                repoList.add(repo)
            }
            repoList

            }
    }

    fun saveRepoData(apiRepoResponse: List<ApiRepoResponse>): CompletableSource? {
        return Completable.fromCallable {
            val repoList = mutableListOf<Repository>()
            apiRepoResponse.forEach {
                val repo = Repository(
                    it.id,
                    it.name,
                    it.private,
                    it.created_at,
                    it.updated_at,
                    it.forks_count,
                    it.stargazers_count,
                    it.watchers_count,
                    it.description,
                    it.language
                )
                repoList.add(repo)
            }
            repositoryDao.insertAll(repoList)
        }

    }

    fun getRepoData(): LiveData<List<Repository>> {
        return repositoryDao.loadRepoList()
    }

}
package br.com.rms.gitconsult.data.repository

import androidx.lifecycle.LiveData
import br.com.rms.gitconsult.data.local.database.dao.UserDao
import br.com.rms.gitconsult.data.local.database.entity.User
import br.com.rms.gitconsult.data.remote.api.GithubApiService
import br.com.rms.gitconsult.data.remote.model.user.ApiUserResponse
import br.com.rms.gitconsult.utils.validations.user.UserValidations
import io.reactivex.Completable
import io.reactivex.CompletableSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val apiService: GithubApiService,
    private val userValidations: UserValidations
) {


    fun loadRemoteUserData(user: String): Completable {
        return Completable.fromCallable { userValidations.validateLoginData(user) }.doOnComplete {
            apiService.loadUserData(user).flatMapCompletable {
                saveUserData(it)
            }
        }

    }

    fun saveUserData(apiUserResponse: ApiUserResponse): CompletableSource? {
        return Completable.fromCallable {
            val user = User(
                apiUserResponse.id,
                apiUserResponse.login,
                apiUserResponse.name,
                apiUserResponse.email,
                apiUserResponse.public_repos,
                apiUserResponse.public_gists,
                apiUserResponse.followers,
                apiUserResponse.following
            )
            userDao.insert(user)
        }
    }

    fun getUserData(): LiveData<List<User>>{
        return userDao.selectUser()
    }


}
package br.com.rms.gitconsult.data.remote.api

import br.com.rms.gitconsult.data.remote.model.repo.ApiRepoResponse
import br.com.rms.gitconsult.data.remote.model.user.ApiUserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET(END_POINT_LOGIN)
    fun loadUserData(@Path(KEY_USER_NAME) user: String): Single<ApiUserResponse>

    @GET(END_POINT_REPOS)
    fun loadUserRepos(
        @Query(KEY_PAGE) page: Int,
        @Query(KEY_PER_PAGE) perPage: Int
    ): Single<List<ApiRepoResponse>>
}
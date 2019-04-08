package br.com.rms.gitconsult.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.rms.gitconsult.base.database.BaseDao
import br.com.rms.gitconsult.data.local.database.entity.Repository

@Dao
interface RepositoryDao : BaseDao<Repository> {

    @Query("SELECT * FROM repository")
    fun loadRepoList(): LiveData<List<Repository>>

    @Insert
    fun insertAll(repositoryList: List<Repository>)
}
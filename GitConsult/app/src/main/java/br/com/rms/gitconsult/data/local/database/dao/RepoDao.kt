package br.com.rms.gitconsult.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.rms.gitconsult.base.database.BaseDao
import br.com.rms.gitconsult.data.local.database.entity.Repo

@Dao
interface RepoDao : BaseDao<Repo> {

    @Query("SELECT * FROM repo")
    fun loadRepoList(): LiveData<List<Repo>>

    @Insert
    fun insertAll(repoList: List<Repo>)
}
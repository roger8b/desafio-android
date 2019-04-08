package br.com.rms.gitconsult.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.rms.gitconsult.base.database.BaseDao
import br.com.rms.gitconsult.data.local.database.entity.User

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * FROM user")
    fun selectUser(): LiveData<List<User>>

    @Query("DELETE FROM user")
    fun deleteAllUsers()

    @Transaction
    fun addNewUser(user: User){
        deleteAllUsers()
        insert(user)
    }

}
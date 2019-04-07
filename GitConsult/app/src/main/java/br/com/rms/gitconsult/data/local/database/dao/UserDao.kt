package br.com.rms.gitconsult.data.local.database.dao

import androidx.room.Dao
import br.com.rms.gitconsult.base.database.BaseDao
import br.com.rms.gitconsult.data.local.database.entity.User

@Dao
interface UserDao : BaseDao<User> {
}
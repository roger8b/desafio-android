package br.com.rms.gitconsult.base.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update


interface BaseDao<T> {

    @Insert
    fun insert(vararg obj:T)

    @Update
    fun update(vararg obj:T)

    @Delete
    fun delete(vararg obj :T)

    @Insert
    fun insertAll(vararg objList: List<T>)



}

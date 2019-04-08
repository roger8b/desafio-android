package br.com.rms.gitconsult.base.database

import androidx.room.*


interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj:T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg obj:T)

    @Delete
    fun delete(vararg obj :T)





}

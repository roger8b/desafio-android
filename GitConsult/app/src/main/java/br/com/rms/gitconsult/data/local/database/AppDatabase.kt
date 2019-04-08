package br.com.rms.gitconsult.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rms.gitconsult.data.local.database.dao.RepositoryDao
import br.com.rms.gitconsult.data.local.database.dao.UserDao
import br.com.rms.gitconsult.data.local.database.entity.User
import br.com.rms.gitconsult.data.local.database.entity.Repository

const val DATABASE_NAME = "gitconsult.db"

@Database(entities = [User::class, Repository::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun repositoryDao(): RepositoryDao

}
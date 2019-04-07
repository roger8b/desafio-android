package br.com.rms.gitconsult.di.model

import android.app.Application
import androidx.room.Room
import br.com.rms.gitconsult.data.local.database.AppDatabase
import br.com.rms.gitconsult.data.local.database.DATABASE_NAME
import br.com.rms.gitconsult.data.local.database.dao.RepoDao
import br.com.rms.gitconsult.data.local.database.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    internal fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    internal fun provideRepoDao(appDatabase: AppDatabase): RepoDao {
        return appDatabase.repoDao()
    }


}
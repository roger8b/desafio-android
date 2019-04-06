package br.com.rms.gitconsult.di.model

import android.app.Application
import androidx.room.Room
import br.com.rms.gitconsult.data.local.database.AppDatabase
import br.com.rms.gitconsult.data.local.database.DATABASE_NAME
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


}
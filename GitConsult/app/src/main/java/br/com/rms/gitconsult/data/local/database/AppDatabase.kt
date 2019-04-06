package br.com.rms.gitconsult.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_NAME = "gitconsult.db"

@Database(entities = [], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

}
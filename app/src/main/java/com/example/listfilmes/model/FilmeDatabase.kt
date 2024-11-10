package com.example.listfilmes.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Filme::class], version = 1)
abstract class FilmeDatabase : RoomDatabase() {

    abstract fun filmeDao(): FilmeDao

    companion object {
        private var INSTANCE: FilmeDatabase? = null

        fun getInstance(context: Context): FilmeDatabase {
            if (INSTANCE == null) {
                synchronized(FilmeDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FilmeDatabase::class.java,
                        "filmes_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}

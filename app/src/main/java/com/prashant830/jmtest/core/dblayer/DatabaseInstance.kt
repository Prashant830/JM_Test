package com.prashant830.jmtest.core.dblayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class DatabaseInstance: RoomDatabase() {
    abstract fun getDao() : Dao

    companion object {
        private var DATABASE_NAME = "IconDatabase"
        @Volatile
         var instance : DatabaseInstance? = null

        fun getDatabase(context: Context): DatabaseInstance? {

            if (instance == null){
                synchronized(DatabaseInstance::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context, DatabaseInstance::class.java,
                        DATABASE_NAME)
                            .build()
                    }
                }
            }
            return instance
        }
    }


}
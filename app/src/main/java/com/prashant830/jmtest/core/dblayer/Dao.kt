package com.prashant830.jmtest.core.dblayer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {


    @Query("SELECT * FROM IconsPreview")
    fun getIconDetail(): Flow<MutableList<Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Entity)
}
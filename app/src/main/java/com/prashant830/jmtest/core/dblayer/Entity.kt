package com.prashant830.jmtest.core.dblayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="IconsPreview")
data class Entity(@ColumnInfo(name = "preveiwUrl") var preveiwUrl: String
                    ,@PrimaryKey @ColumnInfo(name = "iconId") var iconID: Int ,@ColumnInfo(name = "tags") var tags: String
                    ,@ColumnInfo(name = "isPremium") var isPremium: Boolean){
}
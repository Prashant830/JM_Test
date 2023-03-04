package com.prashant830.jmtest.core.networkanddatalayer.repository

import android.content.Context
import com.prashant830.jmtest.core.dblayer.DatabaseInstance
import com.prashant830.jmtest.core.dblayer.Entity
import com.prashant830.jmtest.core.networkanddatalayer.apimanager.ApiResponse
import com.prashant830.jmtest.core.networkanddatalayer.entity.model.IconDetailModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class Repository {

    companion object {

        private var databaseInstance: DatabaseInstance? = null

        fun initaliseDB(context: Context): DatabaseInstance? {
            return DatabaseInstance.getDatabase(context)
        }

        fun insert(context: Context, entity: Entity) {
            databaseInstance = initaliseDB(context)

            CoroutineScope(IO).launch {
                databaseInstance?.getDao()?.insert(entity)
            }

        }

        fun getIconDetail(context: Context): Flow<MutableList<Entity>>? {
            databaseInstance = initaliseDB(context)
            return databaseInstance?.getDao()?.getIconDetail()
        }

        suspend fun fetchApi(): IconDetailModel? {
            var response = CoroutineScope(IO).async {
                ApiResponse.categoryIcApi?.getCateDetailIcons()
            }
            return response.await()?.body()
        }

    }
}
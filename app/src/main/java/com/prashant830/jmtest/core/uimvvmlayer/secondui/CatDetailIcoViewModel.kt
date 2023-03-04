package com.prashant830.jmtest.core.uimvvmlayer.secondui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.prashant830.jmtest.core.dblayer.Entity
import com.prashant830.jmtest.core.networkanddatalayer.entity.model.IconDetailModel
import com.prashant830.jmtest.core.networkanddatalayer.repository.Repository

import kotlinx.coroutines.flow.Flow

class CatDetailIcoViewModel : ViewModel() {

    fun insert(context: Context, entity: Entity){
        Repository.insert(context,entity)
    }

    fun getIconDetail(context: Context): Flow<MutableList<Entity>>? {
        return Repository.getIconDetail(context)
    }

    suspend fun fetchApi() : IconDetailModel? {
        return Repository.fetchApi()
    }

}
package com.prashant830.jmtest.core.uimvvmlayer.thirdui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant830.jmtest.core.networkanddatalayer.apimanager.ApiResponse
import com.prashant830.jmtest.core.networkanddatalayer.entity.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SearchViewModel : ViewModel() {

    suspend fun fetchApitwo(map: Map<String,String>) : SearchModel? {

        var job = viewModelScope.async(Dispatchers.IO){
            ApiResponse.searchIcApi?.getSearchIcon(map as Map<String, String>)

        }
        return  job.await()?.body()
    }
}
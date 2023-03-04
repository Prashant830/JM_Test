package com.prashant830.jmtest.core.networkanddatalayer.apimanager.services

import com.prashant830.jmtest.core.networkanddatalayer.entity.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchIconApi {

    //icons/search?query=arrow&count=10
    @GET("icons/search")
     suspend fun getSearchIcon(@QueryMap map: Map<String,String>) : Response<SearchModel>


}
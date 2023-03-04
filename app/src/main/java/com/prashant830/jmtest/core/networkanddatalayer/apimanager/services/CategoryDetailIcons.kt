package com.prashant830.jmtest.core.networkanddatalayer.apimanager.services

import com.prashant830.jmtest.core.networkanddatalayer.entity.model.IconDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryDetailIcons {
    @GET("iconsets/186091/icons")
    suspend fun getCateDetailIcons() : Response<IconDetailModel>
}
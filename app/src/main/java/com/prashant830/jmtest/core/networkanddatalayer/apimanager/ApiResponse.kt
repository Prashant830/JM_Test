package com.prashant830.jmtest.core.networkanddatalayer.apimanager

import com.prashant830.jmtest.core.networkanddatalayer.apimanager.services.CategoryDetailIcons
import com.prashant830.jmtest.core.networkanddatalayer.apimanager.services.SearchIconApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiResponse {

    var okHttp: OkHttpClient? = null
    //var retrofit: Retrofit? = null
    private val BASE_URL = "https://api.iconfinder.com/v4/"


    fun getRetrofit() : Retrofit?{
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = (HttpLoggingInterceptor.Level.BODY)
            okHttp = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(
                    HeaderInterceptor(
                        "Bearer",
                        "X0vjEUN6KRlxbp2DoUkyHeM0VOmxY91rA6BbU5j3Xu6wDodwS0McmilLPBWDUcJ1"
                    )
                )
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


         // api declarations...
         val searchIcApi = getRetrofit()
             ?.create(SearchIconApi::class.java)
         val categoryIcApi = getRetrofit()
            ?.create(CategoryDetailIcons::class.java)



}




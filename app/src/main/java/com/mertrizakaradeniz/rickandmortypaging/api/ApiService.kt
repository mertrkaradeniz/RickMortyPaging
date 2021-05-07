package com.mertrizakaradeniz.rickandmortypaging.api

import com.mertrizakaradeniz.rickandmortypaging.model.ResponseApi
import com.mertrizakaradeniz.rickandmortypaging.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<ResponseApi>

}
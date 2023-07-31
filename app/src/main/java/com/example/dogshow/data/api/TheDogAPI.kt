package com.example.dogshow.data.api

import com.example.dogshow.data.dto.RespostaCatDogApiDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDogAPI {

    @GET("v1/images/search")
    suspend fun getImages(
        @Query("limit") limit : Int
    ) : Response<RespostaCatDogApiDTO>

}
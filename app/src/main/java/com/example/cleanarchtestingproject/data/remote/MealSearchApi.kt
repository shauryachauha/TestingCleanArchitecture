package com.example.cleanarchtestingproject.data.remote

import com.example.cleanarchtestingproject.data.model.MealsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchApi {
    @GET("api/json/v1/1/search.php")
    suspend fun getMealList(@Query("s") s:String):MealsDto

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealsDetails(@Query("i") i:String):MealsDto
}
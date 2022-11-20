package com.example.cleanarchtestingproject.domain.repository

import com.example.cleanarchtestingproject.data.model.MealsDto

interface GetMealDetailsRepository {
    suspend fun getMealDetails(id:String):MealsDto
}
package com.example.cleanarchtestingproject.data.repository

import com.example.cleanarchtestingproject.data.model.MealsDto
import com.example.cleanarchtestingproject.data.remote.MealSearchApi
import com.example.cleanarchtestingproject.domain.repository.GetMealDetailsRepository

class GetMealsDetailsImpl(private val mealSearchApi: MealSearchApi):GetMealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealsDto {
        return mealSearchApi.getMealsDetails(id)
    }
}
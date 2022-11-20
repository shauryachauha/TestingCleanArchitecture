package com.example.cleanarchtestingproject.data.repository

import com.example.cleanarchtestingproject.data.model.MealsDto
import com.example.cleanarchtestingproject.data.remote.MealSearchApi
import com.example.cleanarchtestingproject.domain.repository.MealSearchReoisitory

class GetMealListImpl(private val mealSearchApi: MealSearchApi):MealSearchReoisitory {
    override suspend fun getMealList(s: String): MealsDto {
        return mealSearchApi.getMealList(s)
    }
}
package com.example.cleanarchtestingproject.domain.repository

import com.example.cleanarchtestingproject.data.model.MealsDto

interface MealSearchReoisitory {
    suspend fun getMealList(s:String):MealsDto
}
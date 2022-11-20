package com.example.cleanarchtestingproject.presentation.meal_search

import com.example.cleanarchtestingproject.domain.model.Meal

data class MealSearchState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)

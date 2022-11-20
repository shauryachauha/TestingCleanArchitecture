package com.example.cleanarchtestingproject.presentation.meal_details

import com.example.cleanarchtestingproject.domain.model.MealDetails

data class MealDetailsState(
    val data: MealDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false
) {
}
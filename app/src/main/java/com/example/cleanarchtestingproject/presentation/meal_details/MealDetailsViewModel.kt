package com.example.cleanarchtestingproject.presentation.meal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchtestingproject.common.Resource
import com.example.cleanarchtestingproject.domain.use_case.GetMealDetailsUseCase
import com.example.cleanarchtestingproject.presentation.meal_search.MealSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val getMealDetailsUseCase: GetMealDetailsUseCase) :
    ViewModel() {
    val _mealDtails = MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDtails

    fun getMealDetails(id: String) {
        getMealDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDtails.value = MealDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDtails.value = MealDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealDtails.value = MealDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}
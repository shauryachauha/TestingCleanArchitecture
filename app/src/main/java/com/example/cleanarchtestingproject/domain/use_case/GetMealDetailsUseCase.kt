package com.example.cleanarchtestingproject.domain.use_case

import com.example.cleanarchtestingproject.common.Resource
import com.example.cleanarchtestingproject.data.model.toDomainMeal
import com.example.cleanarchtestingproject.data.model.toDomainMealDetails
import com.example.cleanarchtestingproject.domain.model.Meal
import com.example.cleanarchtestingproject.domain.model.MealDetails
import com.example.cleanarchtestingproject.domain.repository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val getMealDetailsRepository: GetMealDetailsRepository) {
    operator fun invoke(id:String) :Flow<Resource<MealDetails>> = flow {
        try {
            emit(Resource.Loading())

            val response=getMealDetailsRepository.getMealDetails(id).meals[0].toDomainMealDetails()
            emit(Resource.Success(data = response))

        }catch (e:HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"Unknown Error"))

        }catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?:"Check your internet Connection"))

        }catch (e:Exception){
            
        }

    }
}
package com.example.cleanarchtestingproject.domain.use_case

import com.example.cleanarchtestingproject.common.Resource
import com.example.cleanarchtestingproject.data.model.toDomainMeal
import com.example.cleanarchtestingproject.domain.model.Meal
import com.example.cleanarchtestingproject.domain.repository.MealSearchReoisitory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val mealSearchReoisitory: MealSearchReoisitory) {

    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {

        try {
            emit(Resource.Loading())

            val response=mealSearchReoisitory.getMealList(s)

            val list=if (response.meals.isNullOrEmpty()) emptyList<Meal>()else response.meals.map { it.toDomainMeal() }

            emit(Resource.Success(data = list))

        } catch (e:HttpException){

            emit(Resource.Error(message = e.localizedMessage?:"Unknown Error"))

        } catch (e:IOException){

            emit(Resource.Error(message = e.localizedMessage?:"Check your internet Connection"))

        }catch (e:Exception){


        }
    }
}

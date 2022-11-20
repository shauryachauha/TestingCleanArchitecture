package com.example.cleanarchtestingproject.hilt

import com.example.cleanarchtestingproject.common.Constants
import com.example.cleanarchtestingproject.data.remote.MealSearchApi
import com.example.cleanarchtestingproject.data.repository.GetMealListImpl
import com.example.cleanarchtestingproject.data.repository.GetMealsDetailsImpl
import com.example.cleanarchtestingproject.domain.repository.GetMealDetailsRepository
import com.example.cleanarchtestingproject.domain.repository.MealSearchReoisitory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpModules {

    @Provides
    @Singleton
    fun provideMealSearchAPI():MealSearchApi{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchApi::class.java)
    }


    @Provides
    fun provideMealSearchRepository(mealSearchApi: MealSearchApi):MealSearchReoisitory{
        return GetMealListImpl(mealSearchApi)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchApi: MealSearchApi):GetMealDetailsRepository{
        return GetMealsDetailsImpl(mealSearchApi)
    }
}
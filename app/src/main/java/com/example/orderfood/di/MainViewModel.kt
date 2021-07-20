package com.example.orderfood.di

import androidx.lifecycle.*
import com.example.orderfood.NetworkResult
import com.example.orderfood.models.FoodRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     val repository: Repository
) : ViewModel() {

    var recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafecall(queries)
    }

    suspend fun getRecipesSafecall(queries: Map<String, String>) {
        val response = repository.remote.getRecipes(queries)
        recipesResponse.value = handleFoodAPIResponse(response)
    }

    private fun handleFoodAPIResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe> {
        return when {
            response.isSuccessful -> {
                val foodRecipe = response.body()
                NetworkResult.Success(foodRecipe!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }
}
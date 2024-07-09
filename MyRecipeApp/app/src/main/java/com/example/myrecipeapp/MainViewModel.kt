package com.example.myrecipeapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState : State<RecipeState> = _categoriesState

    private val _randomCategory = mutableStateOf(RandomState())
    val randomCategory : State<RandomState> = _randomCategory

    init {
        //fetchRandomCategory()
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                Log.d("Response",response.toString())
                _categoriesState.value = _categoriesState.value.copy(
                    listCategories = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e:Exception){
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    private fun fetchRandomCategory(){
        viewModelScope.launch {
            try {
                val response = recipeService.randomCategory(RandomRequest(4))
                _randomCategory.value = _randomCategory.value.copy(
                    listRandom = response.message,
                    loading = false,
                    error = null
                )
            }  catch (e:Exception){
                _randomCategory.value = _randomCategory.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading : Boolean = true,
        val listCategories : List<Category> = emptyList(),
        val error : String? = null
    )

    data class RandomState(
        val loading: Boolean = true,
        val listRandom : List<Category> = emptyList(),
        val error: String? = null
    )
}
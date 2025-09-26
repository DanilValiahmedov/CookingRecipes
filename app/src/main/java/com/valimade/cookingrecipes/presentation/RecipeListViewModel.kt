package com.valimade.cookingrecipes.presentation

import androidx.lifecycle.ViewModel
import com.valimade.cookingrecipes.domain.usecase.GetRandomRecipesUseCase
import com.valimade.cookingrecipes.presentation.model.RecipePreviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeListViewModel(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
): ViewModel() {
    private val _recipePreviewState = MutableStateFlow<List<RecipePreviewState>>(emptyList())
    val recipePreviewState: StateFlow<List<RecipePreviewState>> = _recipePreviewState

    init {
        _recipePreviewState.value = listOf(
            RecipePreviewState(
                title = "Паста Карбонара",
                image = "https://images.pexels.com/photos/4518836/pexels-photo-4518836.jpeg?_gl=1*1a1cpeh*_ga*MTMwODAxMTM2Ny4xNzU4ODk4NTcx*_ga_8JE65Q40S6*czE3NTg5MTY5NzMkbzIkZzEkdDE3NTg5MTgzMDkkajQ2JGwwJGgw",
                servings = 2,
                cookingMinutes = 25,
                aggregateLikes = 1200,
                summary = "Классическая итальянская паста с беконом, яйцом и сыром."
            ),
            RecipePreviewState(
                title = "Цезарь с курицей",
                image = "https://images.pexels.com/photos/33674357/pexels-photo-33674357.jpeg?_gl=1*1uwy5ha*_ga*MTMwODAxMTM2Ny4xNzU4ODk4NTcx*_ga_8JE65Q40S6*czE3NTg5MTY5NzMkbzIkZzEkdDE3NTg5MTcwMDckajI2JGwwJGgw",
                servings = 3,
                cookingMinutes = 15,
                aggregateLikes = 980,
                summary = "Лёгкий салат с курицей, сухариками и соусом Цезарь."
            ),
            RecipePreviewState(
                title = "Борщ",
                image = "https://images.pexels.com/photos/8964264/pexels-photo-8964264.jpeg?_gl=1*wpykar*_ga*MTMwODAxMTM2Ny4xNzU4ODk4NTcx*_ga_8JE65Q40S6*czE3NTg5MTY5NzMkbzIkZzEkdDE3NTg5MTcwNjQkajQ2JGwwJGgw",
                servings = 6,
                cookingMinutes = 90,
                aggregateLikes = 1500,
                summary = "Традиционный суп из свёклы, капусты и мяса наваристого бульона."
            ),
            RecipePreviewState(
                title = "Суши ФИладельфия",
                image = "https://images.pexels.com/photos/271715/pexels-photo-271715.jpeg?_gl=1*1wrdcw3*_ga*MTMwODAxMTM2Ny4xNzU4ODk4NTcx*_ga_8JE65Q40S6*czE3NTg5MTY5NzMkbzIkZzEkdDE3NTg5MTcyOTgkajU3JGwwJGgw",
                servings = 4,
                cookingMinutes = 50,
                aggregateLikes = 2200,
                summary = "Популярные роллы с лососем, сливочным сыром и авокадо."
            ),
            RecipePreviewState(
                title = "Шоколадный брауни",
                image = "https://images.pexels.com/photos/6390688/pexels-photo-6390688.jpeg?_gl=1*1xuswx2*_ga*MTMwODAxMTM2Ny4xNzU4ODk4NTcx*_ga_8JE65Q40S6*czE3NTg5MTY5NzMkbzIkZzEkdDE3NTg5MTc1NTckajQ3JGwwJGgw",
                servings = 8,
                cookingMinutes = 40,
                aggregateLikes = 3100,
                summary = "Мягкий и влажный шоколадный десерт для сладкоежек."
            )
        )
    }
}
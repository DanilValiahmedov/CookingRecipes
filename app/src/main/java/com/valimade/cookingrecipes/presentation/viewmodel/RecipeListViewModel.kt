package com.valimade.cookingrecipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.cookingrecipes.domain.usecase.GetRandomRecipesUseCase
import com.valimade.cookingrecipes.presentation.mapper.RecipesMapperUI
import com.valimade.cookingrecipes.presentation.model.RecipePreviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
): ViewModel() {
    private val _recipePreviewState = MutableStateFlow(RecipePreviewState())
    val recipePreviewState = _recipePreviewState.asStateFlow()

    init {
        viewModelScope.launch {
            getRandomRecipesUseCase()
                .onSuccess { recipeDomain ->
                    _recipePreviewState.update {
                        it.copy(
                            isLoading = false,
                            recipeList = recipeDomain.map {
                                RecipesMapperUI.recipePreviewDomainToUI(it)
                            },
                            error = null,
                        )
                    }
                }
                .onFailure { failure ->
                    _recipePreviewState.update {
                        it.copy(
                            isLoading = false,
                            recipeList = emptyList(),
                            error = failure.message,
                        )
                    }
                }
        }
    }

}
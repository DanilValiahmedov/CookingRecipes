package com.valimade.cookingrecipes.di

import com.valimade.cookingrecipes.data.repository.RecipesRepositoryImpl
import com.valimade.cookingrecipes.domain.repository.RecipesRepository
import com.valimade.cookingrecipes.domain.usecase.GetRandomRecipesUseCase
import com.valimade.cookingrecipes.domain.usecase.GetRecipeByIdUseCase
import com.valimade.cookingrecipes.presentation.viewmodel.RecipeListViewModel
import com.valimade.cookingrecipes.presentation.viewmodel.RecipeViewModel
import com.valimade.cookingrecipes.utils.ApiKeys
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val recipesModule = module {
    single<RecipesRepository> {
        RecipesRepositoryImpl(
            httpClient = get(),
            apiKey = ApiKeys.spoonacularApiKey,
            dao = get(),
            mapper = get(),
        )
    }

    singleOf(::GetRandomRecipesUseCase)
    singleOf(::GetRecipeByIdUseCase)

    viewModel{
        RecipeListViewModel(
            getRandomRecipesUseCase = get()
        )
    }

    viewModel{
        RecipeViewModel(
            getRecipeByIdUseCase = get()
        )
    }
}
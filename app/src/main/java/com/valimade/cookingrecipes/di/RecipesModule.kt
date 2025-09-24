package com.valimade.cookingrecipes.di

import com.valimade.cookingrecipes.data.repository.RecipesRepositoryImpl
import com.valimade.cookingrecipes.domain.repository.RecipesRepository
import com.valimade.cookingrecipes.domain.usecase.GetRandomRecipesUseCase
import com.valimade.cookingrecipes.utils.ApiKeys
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val recipesModule = module {
    single<RecipesRepository> {
        RecipesRepositoryImpl(
            httpClient = get(),
            apiKey = ApiKeys.spoonacularApiKey,
        )
    }

    singleOf(::GetRandomRecipesUseCase)
}
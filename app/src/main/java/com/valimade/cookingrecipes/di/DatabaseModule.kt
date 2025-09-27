package com.valimade.cookingrecipes.di

import com.valimade.cookingrecipes.data.database.AppDatabase
import com.valimade.cookingrecipes.data.database.RecipeDao
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        AppDatabase.getDatabase(get())
    }
    
    single<RecipeDao> {
        get<AppDatabase>().recipeDao()
    }
}

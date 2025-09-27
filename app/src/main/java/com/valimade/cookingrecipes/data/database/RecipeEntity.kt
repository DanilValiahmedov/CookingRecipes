package com.valimade.cookingrecipes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String? = null,
    val imageType: String? = null,
    val readyInMinutes: Int? = null,
    val servings: Int? = null,
    val sourceUrl: String? = null,
    val vegetarian: Boolean? = null,
    val vegan: Boolean? = null,
    val glutenFree: Boolean? = null,
    val dairyFree: Boolean? = null,
    val veryHealthy: Boolean? = null,
    val cheap: Boolean? = null,
    val veryPopular: Boolean? = null,
    val sustainable: Boolean? = null,
    val lowFodmap: Boolean? = null,
    val weightWatcherSmartPoints: Int? = null,
    val gaps: String? = null,
    val preparationMinutes: Int? = null,
    val cookingMinutes: Int? = null,
    val aggregateLikes: Int? = null,
    val healthScore: Double? = null,
    val creditsText: String? = null,
    val license: String? = null,
    val sourceName: String? = null,
    val pricePerServing: Double? = null,
    val extendedIngredients: String? = null, // JSON
    val summary: String? = null,
    val cuisines: String? = null, // JSON
    val dishTypes: String? = null, // JSON
    val diets: String? = null, // JSON
    val occasions: String? = null, // JSON
    val instructions: String? = null,
    val analyzedInstructions: String? = null, // JSON
    val spoonacularScore: Double? = null,
    val spoonacularSourceUrl: String? = null
)
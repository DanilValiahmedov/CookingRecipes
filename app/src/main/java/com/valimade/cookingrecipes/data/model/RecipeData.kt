package com.valimade.cookingrecipes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeData(
    val id: Int,
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
    val extendedIngredients: List<ExtendedIngredientData>? = null,
    val summary: String? = null,
    val cuisines: List<String>? = null,
    val dishTypes: List<String>? = null,
    val diets: List<String>? = null,
    val occasions: List<String>? = null,
    val instructions: String? = null,
    val analyzedInstructions: List<AnalyzedInstructionData>? = null,
    val spoonacularScore: Double? = null,
    val spoonacularSourceUrl: String? = null
)

@Serializable
data class ExtendedIngredientData(
    val id: Int,
    val aisle: String? = null,
    val image: String? = null,
    val consistency: String? = null,
    val name: String,
    val nameClean: String? = null,
    val original: String? = null,
    val originalName: String? = null,
    val amount: Double? = null,
    val unit: String? = null,
    val meta: List<String>? = null,
    val measures: MeasuresData? = null
)

@Serializable
data class MeasuresData(
    val us: MeasureUnitData? = null,
    val metric: MeasureUnitData? = null
)

@Serializable
data class MeasureUnitData(
    val amount: Double? = null,
    val unitShort: String? = null,
    val unitLong: String? = null
)

@Serializable
data class AnalyzedInstructionData(
    val name: String? = null,
    val steps: List<InstructionStepData>? = null
)

@Serializable
data class InstructionStepData(
    val number: Int? = null,
    val step: String? = null,
    val ingredients: List<InstructionIngredientData>? = null,
    val equipment: List<InstructionEquipmentData>? = null,
    val length: StepLengthData? = null
)

@Serializable
data class InstructionIngredientData(
    val id: Int,
    val name: String,
    val localizedName: String? = null,
    val image: String? = null
)

@Serializable
data class InstructionEquipmentData(
    val id: Int,
    val name: String,
    val localizedName: String? = null,
    val image: String? = null
)

@Serializable
data class StepLengthData(
    val number: Int? = null,
    val unit: String? = null
)

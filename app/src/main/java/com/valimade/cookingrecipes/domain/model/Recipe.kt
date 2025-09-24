package com.valimade.cookingrecipes.domain.model

data class Recipe(
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
    val extendedIngredients: List<ExtendedIngredient>? = null,
    val summary: String? = null,
    val cuisines: List<String>? = null,
    val dishTypes: List<String>? = null,
    val diets: List<String>? = null,
    val occasions: List<String>? = null,
    val instructions: String? = null,
    val analyzedInstructions: List<AnalyzedInstruction>? = null,
    val spoonacularScore: Double? = null,
    val spoonacularSourceUrl: String? = null
)

data class ExtendedIngredient(
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
    val measures: Measures? = null
)

data class Measures(
    val us: MeasureUnit? = null,
    val metric: MeasureUnit? = null
)

data class MeasureUnit(
    val amount: Double? = null,
    val unitShort: String? = null,
    val unitLong: String? = null
)

data class AnalyzedInstruction(
    val name: String? = null,
    val steps: List<InstructionStep>? = null
)

data class InstructionStep(
    val number: Int? = null,
    val step: String? = null,
    val ingredients: List<InstructionIngredient>? = null,
    val equipment: List<InstructionEquipment>? = null,
    val length: StepLength? = null
)

data class InstructionIngredient(
    val id: Int,
    val name: String,
    val localizedName: String? = null,
    val image: String? = null
)

data class InstructionEquipment(
    val id: Int,
    val name: String,
    val localizedName: String? = null,
    val image: String? = null
)

data class StepLength(
    val number: Int? = null,
    val unit: String? = null
)

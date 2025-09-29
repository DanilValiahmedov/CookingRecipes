package com.valimade.cookingrecipes.data.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valimade.cookingrecipes.data.database.RecipeEntity
import com.valimade.cookingrecipes.data.model.AnalyzedInstructionData
import com.valimade.cookingrecipes.data.model.ExtendedIngredientData
import com.valimade.cookingrecipes.data.model.InstructionEquipmentData
import com.valimade.cookingrecipes.data.model.InstructionIngredientData
import com.valimade.cookingrecipes.data.model.InstructionStepData
import com.valimade.cookingrecipes.data.model.MeasureUnitData
import com.valimade.cookingrecipes.data.model.MeasuresData
import com.valimade.cookingrecipes.data.model.RecipeData
import com.valimade.cookingrecipes.data.model.StepLengthData
import com.valimade.cookingrecipes.domain.model.AnalyzedInstruction
import com.valimade.cookingrecipes.domain.model.ExtendedIngredient
import com.valimade.cookingrecipes.domain.model.InstructionEquipment
import com.valimade.cookingrecipes.domain.model.InstructionIngredient
import com.valimade.cookingrecipes.domain.model.InstructionStep
import com.valimade.cookingrecipes.domain.model.MeasureUnit
import com.valimade.cookingrecipes.domain.model.Measures
import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.domain.model.StepLength
import java.lang.reflect.Type

object RecipesMapperData {
    
    private val gson = Gson()
    
    fun recipeDataToDomain(recipe: RecipeData): Recipe {
        return Recipe(
            id = recipe.id,
            title = recipe.title,
            image = recipe.image,
            imageType = recipe.imageType,
            readyInMinutes = recipe.readyInMinutes,
            servings = recipe.servings,
            sourceUrl = recipe.sourceUrl,
            vegetarian = recipe.vegetarian,
            vegan = recipe.vegan,
            glutenFree = recipe.glutenFree,
            dairyFree = recipe.dairyFree,
            veryHealthy = recipe.veryHealthy,
            cheap = recipe.cheap,
            veryPopular = recipe.veryPopular,
            sustainable = recipe.sustainable,
            lowFodmap = recipe.lowFodmap,
            weightWatcherSmartPoints = recipe.weightWatcherSmartPoints,
            gaps = recipe.gaps,
            preparationMinutes = recipe.preparationMinutes,
            cookingMinutes = recipe.cookingMinutes,
            aggregateLikes = recipe.aggregateLikes,
            healthScore = recipe.healthScore,
            creditsText = recipe.creditsText,
            license = recipe.license,
            sourceName = recipe.sourceName,
            pricePerServing = recipe.pricePerServing,
            extendedIngredients = recipe.extendedIngredients?.map { mapExtendedIngredient(it) },
            summary = recipe.summary,
            cuisines = recipe.cuisines,
            dishTypes = recipe.dishTypes,
            diets = recipe.diets,
            occasions = recipe.occasions,
            instructions = recipe.instructions,
            analyzedInstructions = recipe.analyzedInstructions?.map { mapAnalyzedInstruction(it) },
            spoonacularScore = recipe.spoonacularScore,
            spoonacularSourceUrl = recipe.spoonacularSourceUrl
        )
    }

    private fun mapExtendedIngredient(data: ExtendedIngredientData): ExtendedIngredient {
        return ExtendedIngredient(
            id = data.id,
            aisle = data.aisle,
            image = data.image,
            consistency = data.consistency,
            name = data.name,
            nameClean = data.nameClean,
            original = data.original,
            originalName = data.originalName,
            amount = data.amount,
            unit = data.unit,
            meta = data.meta,
            measures = data.measures?.let { mapMeasures(it) }
        )
    }

    private fun mapMeasures(data: MeasuresData): Measures {
        return Measures(
            us = data.us?.let { mapMeasureUnit(it) },
            metric = data.metric?.let { mapMeasureUnit(it) }
        )
    }

    private fun mapMeasureUnit(data: MeasureUnitData): MeasureUnit {
        return MeasureUnit(
            amount = data.amount,
            unitShort = data.unitShort,
            unitLong = data.unitLong
        )
    }

    private fun mapAnalyzedInstruction(data: AnalyzedInstructionData): AnalyzedInstruction {
        return AnalyzedInstruction(
            name = data.name,
            steps = data.steps?.map { mapInstructionStep(it) }
        )
    }

    private fun mapInstructionStep(data: InstructionStepData): InstructionStep {
        return InstructionStep(
            number = data.number,
            step = data.step,
            ingredients = data.ingredients?.map { mapInstructionIngredient(it) },
            equipment = data.equipment?.map { mapInstructionEquipment(it) },
            length = data.length?.let { mapStepLength(it) }
        )
    }

    private fun mapInstructionIngredient(data: InstructionIngredientData): InstructionIngredient {
        return InstructionIngredient(
            id = data.id,
            name = data.name,
            localizedName = data.localizedName,
            image = data.image
        )
    }

    private fun mapInstructionEquipment(data: InstructionEquipmentData): InstructionEquipment {
        return InstructionEquipment(
            id = data.id,
            name = data.name,
            localizedName = data.localizedName,
            image = data.image
        )
    }

    private fun mapStepLength(data: StepLengthData): StepLength {
        return StepLength(
            number = data.number,
            unit = data.unit
        )
    }
    
    fun recipeEntityToDomain(entity: RecipeEntity): Recipe {
        return Recipe(
            id = entity.id,
            title = entity.title,
            image = entity.image,
            imageType = entity.imageType,
            readyInMinutes = entity.readyInMinutes,
            servings = entity.servings,
            sourceUrl = entity.sourceUrl,
            vegetarian = entity.vegetarian,
            vegan = entity.vegan,
            glutenFree = entity.glutenFree,
            dairyFree = entity.dairyFree,
            veryHealthy = entity.veryHealthy,
            cheap = entity.cheap,
            veryPopular = entity.veryPopular,
            sustainable = entity.sustainable,
            lowFodmap = entity.lowFodmap,
            weightWatcherSmartPoints = entity.weightWatcherSmartPoints,
            gaps = entity.gaps,
            preparationMinutes = entity.preparationMinutes,
            cookingMinutes = entity.cookingMinutes,
            aggregateLikes = entity.aggregateLikes,
            healthScore = entity.healthScore,
            creditsText = entity.creditsText,
            license = entity.license,
            sourceName = entity.sourceName,
            pricePerServing = entity.pricePerServing,
            extendedIngredients = entity.extendedIngredients?.let { 
                parseJsonToList(it, object : TypeToken<List<ExtendedIngredient>>() {}.type)
            },
            summary = entity.summary,
            cuisines = entity.cuisines?.let { 
                parseJsonToList(it, object : TypeToken<List<String>>() {}.type)
            },
            dishTypes = entity.dishTypes?.let { 
                parseJsonToList(it, object : TypeToken<List<String>>() {}.type)
            },
            diets = entity.diets?.let { 
                parseJsonToList(it, object : TypeToken<List<String>>() {}.type)
            },
            occasions = entity.occasions?.let { 
                parseJsonToList(it, object : TypeToken<List<String>>() {}.type)
            },
            instructions = entity.instructions,
            analyzedInstructions = entity.analyzedInstructions?.let { 
                parseJsonToList(it, object : TypeToken<List<AnalyzedInstruction>>() {}.type)
            },
            spoonacularScore = entity.spoonacularScore,
            spoonacularSourceUrl = entity.spoonacularSourceUrl
        )
    }
    
    @Suppress("UNCHECKED_CAST")
    private fun <T> parseJsonToList(json: String, type: Type): List<T>? {
        return try {
            gson.fromJson(json, type) as? List<T>
        } catch (e: Exception) {
            null
        }
    }

    fun recipeDataToEntity(recipeData: RecipeData): RecipeEntity {
        val gson = Gson()

        return RecipeEntity(
            id = recipeData.id,
            title = recipeData.title,
            image = recipeData.image,
            imageType = recipeData.imageType,
            readyInMinutes = recipeData.readyInMinutes,
            servings = recipeData.servings,
            sourceUrl = recipeData.sourceUrl,
            vegetarian = recipeData.vegetarian,
            vegan = recipeData.vegan,
            glutenFree = recipeData.glutenFree,
            dairyFree = recipeData.dairyFree,
            veryHealthy = recipeData.veryHealthy,
            cheap = recipeData.cheap,
            veryPopular = recipeData.veryPopular,
            sustainable = recipeData.sustainable,
            lowFodmap = recipeData.lowFodmap,
            weightWatcherSmartPoints = recipeData.weightWatcherSmartPoints,
            gaps = recipeData.gaps,
            preparationMinutes = recipeData.preparationMinutes,
            cookingMinutes = recipeData.cookingMinutes,
            aggregateLikes = recipeData.aggregateLikes,
            healthScore = recipeData.healthScore,
            creditsText = recipeData.creditsText,
            license = recipeData.license,
            sourceName = recipeData.sourceName,
            pricePerServing = recipeData.pricePerServing,
            extendedIngredients = recipeData.extendedIngredients?.let {
                gson.toJson(it)
            },
            summary = recipeData.summary,
            cuisines = recipeData.cuisines?.let {
                gson.toJson(it)
            },
            dishTypes = recipeData.dishTypes?.let {
                gson.toJson(it)
            },
            diets = recipeData.diets?.let {
                gson.toJson(it)
            },
            occasions = recipeData.occasions?.let {
                gson.toJson(it)
            },
            instructions = recipeData.instructions,
            analyzedInstructions = recipeData.analyzedInstructions?.let {
                gson.toJson(it)
            },
            spoonacularScore = recipeData.spoonacularScore,
            spoonacularSourceUrl = recipeData.spoonacularSourceUrl
        )

    }
}
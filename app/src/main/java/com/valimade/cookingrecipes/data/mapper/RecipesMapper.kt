package com.valimade.cookingrecipes.data.mapper

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

object RecipesMapper {
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
}
package com.valimade.cookingrecipes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: RecipeEntity)

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getById(id: Int): RecipeEntity?

    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<RecipeEntity>
}
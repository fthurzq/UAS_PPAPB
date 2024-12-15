package com.example.uas_ppapb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(recipe: Recipe)

    @Query("SELECT * FROM recipes WHERE name LIKE :query")
    suspend fun search(query: String): List<Recipe>

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>
}
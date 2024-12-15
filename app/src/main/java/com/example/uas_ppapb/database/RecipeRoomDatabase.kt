package com.example.uas_ppapb.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeRoomDatabase : RoomDatabase(){
    abstract fun recipeDao(): RecipeDao
}
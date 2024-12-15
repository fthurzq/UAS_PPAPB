package com.example.uas_ppapb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_ppapb.databinding.ActivityItemRecipeCardBinding

class ItemRecipeCard : AppCompatActivity() {

    // Data class untuk menyimpan informasi resep
    data class RecipeItem(
        val title: String,
        val category: String
    )

    // Lateinit untuk binding layout
    private lateinit var binding: ActivityItemRecipeCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout menggunakan ViewBinding
        binding = ActivityItemRecipeCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Contoh penggunaan: menampilkan data resep
        val recipe = RecipeItem("Nasi Goreng", "Makanan Utama")
        displayRecipe(recipe)
    }

    // Fungsi untuk menampilkan data resep pada tampilan
    private fun displayRecipe(recipe: RecipeItem) {
        binding.recipeTitle.text = recipe.title
        binding.recipeCategory.text = recipe.category
    }
}

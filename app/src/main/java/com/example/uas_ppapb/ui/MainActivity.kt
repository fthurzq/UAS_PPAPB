package com.example.uas_ppapb.ui

import RecipeApi
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.uas_ppapb.R
import com.example.uas_ppapb.database.Recipe
import com.example.uas_ppapb.database.RecipeDao
import com.example.uas_ppapb.database.RecipeRoomDatabase
import com.example.uas_ppapb.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val api: RecipeApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://ppbo-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appTitle : TextView = findViewById(R.id.appTitle)
        val searchInput : EditText = findViewById(R.id.searchInput)
        val searchButton : Button = findViewById(R.id.searchButton)
        val recipeListContainer : LinearLayout = findViewById(R.id.recipeListContainer)
        val addRecipeButton: Button = findViewById(R.id.addRecipeButton)

        // Fetch recipes from API on Activity launch
        fetchRecipes(recipeListContainer)

        searchButton.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isNotEmpty()) {
                searchRecipe(query, recipeListContainer)
            } else {
                searchInput.error = "Kata kunci tidak boleh kosong"
            }
        }

        // Event: Tombol Tambah Resep
        addRecipeButton.setOnClickListener {
            addRecipe(recipeListContainer)
        }

        // Inisialisasi daftar resep awal
        initRecipeList(recipeListContainer)
    }

    private fun fetchRecipes(container: LinearLayout) {
        api.getRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful) {
                    response.body()?.let { recipes ->
                        // Display recipes in the UI
                        for (recipe in recipes) {
                            val recipeItem = TextView(this@MainActivity)
                            recipeItem.text = recipe.name
                            recipeItem.textSize = 16f
                            recipeItem.setPadding(8, 8, 8, 8)
                            container.addView(recipeItem)
                        }
                    }
                } else {
                    // Handle error case
                    val errorItem = TextView(this@MainActivity)
                    errorItem.text = "Error: Unable to fetch data"
                    container.addView(errorItem)
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                // Handle failure case
                val failureItem = TextView(this@MainActivity)
                failureItem.text = "Error: ${t.message}"
                container.addView(failureItem)
            }
        })
    }

    // Fungsi untuk mencari resep (mock data)
    private fun searchRecipe(query: String, container: LinearLayout) {
        // Kosongkan container sebelumnya
        container.removeAllViews()

        // Mock pencarian resep
        val recipes = listOf("Nasi Goreng", "Mie Goreng", "Ayam Bakar", "Sate Ayam")
        val filteredRecipes = recipes.filter { it.contains(query, ignoreCase = true) }

        // Tampilkan hasil pencarian
        if (filteredRecipes.isNotEmpty()) {
            for (recipe in filteredRecipes) {
                val recipeItem = TextView(this)
                recipeItem.text = recipe
                recipeItem.textSize = 16f
                recipeItem.setPadding(8, 8, 8, 8)
                container.addView(recipeItem)
            }
        } else {
            val noResult = TextView(this)
            noResult.text = "Resep tidak ditemukan."
            noResult.textSize = 16f
            noResult.setPadding(8, 8, 8, 8)
            container.addView(noResult)
        }
    }

    // Fungsi untuk menambah resep (mock data)
    private fun addRecipe(container: LinearLayout) {
        val newRecipe = TextView(this)
        newRecipe.text = "Resep Baru - ${System.currentTimeMillis()}"
        newRecipe.textSize = 16f
        newRecipe.setPadding(8, 8, 8, 8)
        container.addView(newRecipe)
    }

    // Fungsi untuk inisialisasi daftar resep
    private fun initRecipeList(container: LinearLayout) {
        val initialRecipes = listOf("Nasi Goreng", "Mie Goreng", "Ayam Bakar", "Sate Ayam")
        for (recipe in initialRecipes) {
            val recipeItem = TextView(this)
            recipeItem.text = recipe
            recipeItem.textSize = 16f
            recipeItem.setPadding(8, 8, 8, 8)
            container.addView(recipeItem)
        }
    }
}
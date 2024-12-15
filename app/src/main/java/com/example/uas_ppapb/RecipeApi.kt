import com.example.uas_ppapb.database.Recipe
import retrofit2.Call
import retrofit2.http.GET

interface RecipeApi {
    @GET("IBNZw/recipe")
    fun getRecipes(): Call<List<Recipe>>
}

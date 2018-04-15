package baking.com.baking.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import baking.com.baking.Adapters.IngredientsAdapter;
import baking.com.baking.IngredientsService;
import baking.com.baking.Models.IngredientModel;
import baking.com.baking.Utils.BakingRecipeJsonUtils;
import baking.com.baking.Utils.NetworkUtils;


public class FetchIngredientsAsyncTask extends AsyncTask<Void, Void, ArrayList<IngredientModel>> {
    private final RecyclerView recyclerView;
    private final IngredientsAdapter ingredientsAdapter;
    private ArrayList<IngredientModel> ingredientModels = new ArrayList<>();
    private final int recipeId;
    private Context context;
    public static ArrayList<IngredientModel> staticIngredientModels = new ArrayList<>();


    public FetchIngredientsAsyncTask(RecyclerView recyclerView, IngredientsAdapter ingredientsAdapter, int recipeId,Context context) {
        this.recyclerView = recyclerView;
        this.ingredientsAdapter = ingredientsAdapter;
        this.recipeId = recipeId;
        this.context = context;

    }

    @Override
    protected ArrayList<IngredientModel> doInBackground(Void... Void) {

        try {
            String recipesJsonData = NetworkUtils.getResponseFromHttp();


            ingredientModels = BakingRecipeJsonUtils.getIngredientsDataFromJson(recipeId, recipesJsonData);


            return ingredientModels;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException je) {
            je.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<IngredientModel> ingredientModels) {
        super.onPostExecute(ingredientModels);
        if (ingredientsAdapter != null) {
            ingredientsAdapter.addAllItem(ingredientModels);
            recyclerView.setAdapter(ingredientsAdapter);
            FetchIngredientsAsyncTask.staticIngredientModels = ingredientModels;
            IngredientsService.getIngredients(context);



        }



    }

}

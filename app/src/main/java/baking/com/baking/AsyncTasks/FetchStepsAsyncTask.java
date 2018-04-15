package baking.com.baking.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import baking.com.baking.Adapters.StepsAdapter;
import baking.com.baking.Models.RecipeStepsModel;
import baking.com.baking.Utils.BakingRecipeJsonUtils;
import baking.com.baking.Utils.NetworkUtils;


public class FetchStepsAsyncTask extends AsyncTask<Void, Void, ArrayList<RecipeStepsModel>> {
    private final RecyclerView recyclerView;
    private final StepsAdapter stepsAdapter;
    private ArrayList<RecipeStepsModel> recipeStepsModels = new ArrayList<>();
    private final int recipeId;


    public FetchStepsAsyncTask(RecyclerView recyclerView, StepsAdapter stepsAdapter, int recipeId) {
        this.recyclerView = recyclerView;
        this.stepsAdapter = stepsAdapter;
        this.recipeId = recipeId;

    }

    @Override
    protected ArrayList<RecipeStepsModel> doInBackground(Void... Void) {

        try {
            String recipesJsonData = NetworkUtils.getResponseFromHttp();


            recipeStepsModels = BakingRecipeJsonUtils.getRecipeStepsModelsFromJson(recipeId, recipesJsonData);

            return recipeStepsModels;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<RecipeStepsModel> recipeStepsModels) {
        super.onPostExecute(recipeStepsModels);
        if (stepsAdapter != null) {
            stepsAdapter.addAllItem(recipeStepsModels);
            recyclerView.setAdapter(stepsAdapter);
        }


    }
}


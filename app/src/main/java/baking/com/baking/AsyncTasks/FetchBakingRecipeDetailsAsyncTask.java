package baking.com.baking.AsyncTasks;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import baking.com.baking.Adapters.BakingRecipesAdapter;
import baking.com.baking.Adapters.IngredientsAdapter;
import baking.com.baking.Models.BakingModel;
import baking.com.baking.Models.BakingTopModel;
import baking.com.baking.Utils.BakingRecipeJsonUtils;
import baking.com.baking.Utils.NetworkUtils;


public class FetchBakingRecipeDetailsAsyncTask extends AsyncTask<Void, Void, ArrayList<BakingModel>> {
    private final RecyclerView recyclerView;
    private final BakingRecipesAdapter bakingRecipesAdapter;
    IngredientsAdapter ingredientsAdapter;
    private ArrayList<BakingModel> bakingModels = new ArrayList<>();


    public FetchBakingRecipeDetailsAsyncTask(RecyclerView recyclerView, BakingRecipesAdapter bakingRecipesAdapter) {
        this.recyclerView = recyclerView;
        this.bakingRecipesAdapter = bakingRecipesAdapter;
    }


    @Override
    protected ArrayList<BakingModel> doInBackground(Void... Void) {

        try {
            String recipesJsonData = NetworkUtils.getResponseFromHttp();

            bakingModels = BakingRecipeJsonUtils.getBakingDataFromJson(recipesJsonData);

            return bakingModels;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<BakingModel> bakingModels) {
        super.onPostExecute(bakingModels);
        if (bakingRecipesAdapter != null) {
            bakingRecipesAdapter.addAllItems(bakingModels);
            recyclerView.setAdapter(bakingRecipesAdapter);
        }


    }
}

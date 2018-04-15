package baking.com.baking.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import baking.com.baking.Adapters.BakingRecipesAdapter;
import baking.com.baking.Adapters.IngredientsAdapter;
import baking.com.baking.AsyncTasks.FetchBakingRecipeDetailsAsyncTask;
import baking.com.baking.AsyncTasks.FetchIngredientsAsyncTask;
import baking.com.baking.IngredientsService;
import baking.com.baking.Models.BakingModel;
import baking.com.baking.Models.IngredientModel;
import baking.com.baking.R;
import baking.com.baking.RecipeClickListener;



public class IngredientsFragment extends Fragment {
    private RecyclerView recyclerView;
    public static final String INGREDIENTS = "INgredients";
    private int recipeId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.recipe_recycler_view,container,false);
        recipeId = getArguments().getInt("RecipeId");
        recyclerView = view.findViewById(R.id.recipe_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
        new FetchIngredientsAsyncTask(recyclerView, ingredientsAdapter , recipeId,getContext()).execute();



        return view;
    }
}

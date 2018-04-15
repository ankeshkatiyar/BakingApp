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
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import baking.com.baking.Adapters.BakingRecipesAdapter;
import baking.com.baking.AsyncTasks.FetchBakingRecipeDetailsAsyncTask;
import baking.com.baking.Models.BakingModel;
import baking.com.baking.Models.IngredientModel;
import baking.com.baking.R;
import baking.com.baking.RecipeClickListener;

public class RecipesFragment extends Fragment {
    private RecyclerView recyclerView;
    public static String  recipeName="" ;
    private BakingRecipesAdapter bakingRecipesAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recipe_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        bakingRecipesAdapter = new BakingRecipesAdapter(new RecipeClickListener() {
            @Override
            public void RecipeClicked(BakingModel bakingModel, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("RecipeId" , bakingModel.getRecipeId());
                bundle.putInt("Position",0);
                recipeName = bakingModel.getRecipeName();
                IngredientAndStepsContainerFragment ingredientAndStepsContainerFragment = new IngredientAndStepsContainerFragment();
                ingredientAndStepsContainerFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.recipe_frag, ingredientAndStepsContainerFragment, "ing_frag").commit();


            }
        });
        new FetchBakingRecipeDetailsAsyncTask(recyclerView, bakingRecipesAdapter).execute();
        return view;
    }
}

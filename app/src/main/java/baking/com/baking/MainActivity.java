package baking.com.baking;



import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import baking.com.baking.Adapters.BakingRecipesAdapter;
import baking.com.baking.AsyncTasks.FetchBakingRecipeDetailsAsyncTask;
import baking.com.baking.Fragments.IngredientsFragment;
import baking.com.baking.Fragments.RecipesFragment;
import baking.com.baking.Models.BakingModel;
import baking.com.baking.Models.BakingTopModel;

public class MainActivity extends AppCompatActivity {


private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(savedInstanceState==null) {

            RecipesFragment recipesFragment = new RecipesFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.recipe_frag, recipesFragment, "ing_frag").commit();
        }else{
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "RecipesFragment");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getSupportFragmentManager().putFragment(outState,"RecipesFragment",mContent);
    }
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}


package baking.com.baking.Fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
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
import android.widget.Toast;

import java.util.ArrayList;

import baking.com.baking.Adapters.StepsAdapter;
import baking.com.baking.AsyncTasks.FetchStepsAsyncTask;
import baking.com.baking.Database.BakingContentProviders;
import baking.com.baking.Database.BakingContractClass;
import baking.com.baking.Models.RecipeStepsModel;
import baking.com.baking.R;
import baking.com.baking.StepsClickListener;


public class MainStepsFragment extends Fragment {
    private RecyclerView recyclerView;
    private int recipeId;
    private int stepId;
    private ContentValues contentValues;

    public static final String STEPS = "Steps";
    private final ArrayList<Integer> videosWatched = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recipe_recycler_view, container, false);
        recipeId = getArguments().getInt("RecipeId");
        getWatchedStepsVideos();
        recyclerView = view.findViewById(R.id.recipe_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        StepsAdapter stepsAdapter = new StepsAdapter(new StepsClickListener() {
            @Override
            public void StepsClickListener(RecipeStepsModel recipeStepsModel, int position) {


                Bundle bundle = new Bundle();
                stepId = recipeStepsModel.getStepId();
                contentValues = new ContentValues();
                contentValues.put(BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID,recipeId);
                contentValues.put(BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID,stepId);
                contentValues.put(BakingContractClass.RecipeStepsWatched.COLUMN_WATCHED, 1);
                updateDatabaseStepsWatched();
                bundle.putString("VideoUrl", recipeStepsModel.getVideoURL());
                bundle.putInt("RecipeId", recipeId);
                bundle.putString("StepsDescription", recipeStepsModel.getDescription());
                bundle.putString("Thumbnail", recipeStepsModel.getThumbnailURL());
                StepsDetailsFragment stepsDetailsFragment = new StepsDetailsFragment();
                stepsDetailsFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.recipe_frag, stepsDetailsFragment, "ing_frag").commit();
            }
        }, videosWatched);

        new FetchStepsAsyncTask(recyclerView, stepsAdapter, recipeId).execute();


        return view;
    }

    private void updateDatabaseStepsWatched() {

        ContentResolver contentResolver = getActivity().getContentResolver();
        String[] tableColumns = {BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID,
                BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID,
                BakingContractClass.RecipeStepsWatched.COLUMN_WATCHED};
        String whereClause = BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID + " = ?  AND " +
                BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID + " = ? ";
        String whereArguments[] = {Integer.toString(recipeId), Integer.toString(stepId)};
        if(checkIfAlreadyExistInDB()) {
           contentResolver.update(BakingContentProviders.CONTENT_URL, contentValues, whereClause, whereArguments);
        }
        else {
            contentResolver.insert(BakingContentProviders.CONTENT_URL,contentValues);
        }

    }

    private void getWatchedStepsVideos() {

        ContentResolver contentResolver = getActivity().getContentResolver();
        String[] tableColumns = {BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID,
                BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID,
                BakingContractClass.RecipeStepsWatched.COLUMN_WATCHED};
        String whereClause = BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID + " = ? ";
        String whereArguments[] = {Integer.toString(recipeId)};
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(BakingContentProviders.CONTENT_URL, tableColumns, whereClause, whereArguments, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int watch  =  cursor.getInt(cursor.getColumnIndex(BakingContractClass.RecipeStepsWatched.COLUMN_WATCHED));
                    int recipe_id = cursor.getInt(cursor.getColumnIndex(BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID));
                    int step_id = cursor.getInt(cursor.getColumnIndex(BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID));

                    if (watch == 1) {
                        videosWatched.add(cursor.getInt(cursor.getColumnIndex(BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID)));
                    }

                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }


    }

   private boolean checkIfAlreadyExistInDB(){

       ContentResolver contentResolver = getActivity().getContentResolver();
       String[] tableColumns = {BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID,
               BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID};
       String whereClause = BakingContractClass.RecipeStepsWatched.COLUMN_RECIPE_ID + " = ?  AND " +
               BakingContractClass.RecipeStepsWatched.COLUMN_STEP_ID + " = ? ";
       String whereArguments[] = {Integer.toString(recipeId) , Integer.toString(stepId)};
       Cursor cursor = null;
       try {
           cursor = contentResolver.query(BakingContentProviders.CONTENT_URL, tableColumns, whereClause, whereArguments, null);
           if (cursor != null && cursor.moveToFirst()) {
               cursor.close();
               return  true;

               }
               else{
               cursor.close();
               return  false;
           }

           }
        catch (NullPointerException npe) {
           npe.printStackTrace();
       }
       return  false;
   }
}

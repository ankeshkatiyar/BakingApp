package baking.com.baking;

/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import java.util.ArrayList;

import baking.com.baking.AsyncTasks.FetchIngredientsAsyncTask;
import baking.com.baking.Fragments.RecipesFragment;
import baking.com.baking.Models.IngredientModel;
import baking.com.baking.R;




public class IngredintsWidgetRemoteService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewRemoteViewsFactory(this.getApplicationContext());
    }
}

class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    ArrayList<IngredientModel> ingredientModels = new ArrayList<>();

    public ListViewRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;

    }

    @Override
    public void onCreate() {

    }

    //called on start and when notifyAppWidgetViewDataChanged is called
    @Override
    public void onDataSetChanged() {
        // Get all plant info ordered by creation time
        ingredientModels = FetchIngredientsAsyncTask.staticIngredientModels;

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {

        return FetchIngredientsAsyncTask.staticIngredientModels.size();
    }


    @Override
    public RemoteViews getViewAt(int position) {


        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        String temp = "";
        try {
           temp = ingredientModels.get(position).getIngredient();
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        views.setTextViewText(R.id.widget_item,temp);


        return views;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1; // Treat all items in the GridView the same
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
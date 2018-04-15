package baking.com.baking;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import baking.com.baking.Models.IngredientModel;

public class IngredientsService extends IntentService {

    public static final String ACTION_INGREDIENT = "baking.com.baking.action.ingredients";
    private static ArrayList<IngredientModel> ingredientModel = new ArrayList<>();


    public IngredientsService() {
        super("IngredientsService");
    }

    public static void  getIngredients(Context context ){

        Intent intent = new Intent(context ,IngredientsService.class);
        intent.setAction(ACTION_INGREDIENT);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent !=null){
            final String action = intent.getAction();
            if(ACTION_INGREDIENT.equals(action)){
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,IngredientsWidget.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.widget_list_view);
                IngredientsWidget.updateIngredientWidget(this,appWidgetManager,appWidgetIds);
            }
        }

    }


}

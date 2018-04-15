package baking.com.baking.Database;

import android.provider.BaseColumns;

public class BakingContractClass  {

    public class RecipeStepsWatched implements BaseColumns{
        public static final String TABLE_NAME= "recipe_steps";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_STEP_ID = "step_id";
        public static final String COLUMN_WATCHED = "watched";


    }
}

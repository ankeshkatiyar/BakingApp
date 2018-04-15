package baking.com.baking.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import baking.com.baking.Models.BakingModel;
import baking.com.baking.Models.BakingTopModel;
import baking.com.baking.Models.IngredientModel;
import baking.com.baking.Models.RecipeStepsModel;

public class BakingRecipeJsonUtils {

    private static final String RECIPE_ID = "id";
    private static final String RECIPE_NAME = "name";
    private static final String RECIPE_INGREDIENTS = "ingredients";
    private static final String RECIPE_STEPS = "steps";
    private static final String RECIPE_SERVINGS = "servings";
    private static final String RECIPE_IMAGE = "image";
    private static final String INGREDIENTS_QUANTITY = "quantity";
    private static final String INGREDIENTS_MEASURE = "measure";
    private static final String INGREDIENTS_INGREDIENT = "ingredient";
    private static final String STEPS_ID = "id";
    private static final String STEPS_SHORT_DESCRIPTION = "shortDescription";
    private static final String STEPS_DESCRIPTION = "description";
    private static final String STEPS_VIDEO_URL = "videoURL";
    private static final String STEPS_THUMBNAIL_URL = "thumbnailURL";



    public static BakingTopModel getBakingRecipeDataFromJSON(String JsonString) {


        ArrayList<BakingModel> bakingModels = new ArrayList<>();
        ArrayList<IngredientModel> ingredientModels = new ArrayList<>();
        ArrayList<RecipeStepsModel> recipeStepsModels = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(JsonString);

            int numberOfRecipes = jsonArray.length();
            for (int i = 0; i < numberOfRecipes; i++) {

                JSONObject eachRecipeObject = jsonArray.getJSONObject(i);

                bakingModels.add(new BakingModel(eachRecipeObject.getInt(RECIPE_ID)
                        , eachRecipeObject.getString(RECIPE_NAME), eachRecipeObject.getString(RECIPE_IMAGE),
                        eachRecipeObject.getInt(RECIPE_SERVINGS)));


                JSONArray jsonArray1 = eachRecipeObject.getJSONArray(RECIPE_INGREDIENTS);


                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject eachRecipeIngredientsObject = jsonArray1.getJSONObject(j);

                    ingredientModels.add(new IngredientModel(eachRecipeIngredientsObject.getString(INGREDIENTS_MEASURE)
                            , eachRecipeIngredientsObject.getString(INGREDIENTS_INGREDIENT),
                            eachRecipeIngredientsObject.getInt(INGREDIENTS_QUANTITY)));
                }


                jsonArray1 = eachRecipeObject.getJSONArray(RECIPE_STEPS);


                for (int k = 0; k < jsonArray1.length(); k++) {
                    JSONObject eachRecipeStepsObject = jsonArray1.getJSONObject(k);

                    recipeStepsModels.add(new RecipeStepsModel(eachRecipeStepsObject.getInt(STEPS_ID),
                            eachRecipeStepsObject.getString(STEPS_SHORT_DESCRIPTION),
                            eachRecipeStepsObject.getString(STEPS_DESCRIPTION),
                            eachRecipeStepsObject.getString(STEPS_VIDEO_URL),
                            eachRecipeStepsObject.getString(STEPS_THUMBNAIL_URL)));


                }


            }

            return new BakingTopModel(bakingModels, ingredientModels, recipeStepsModels);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static ArrayList<BakingModel> getBakingDataFromJson(String JsonString) {
        ArrayList<BakingModel> bakingModels = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(JsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eachRecipeObject = jsonArray.getJSONObject(i);
                bakingModels.add(new BakingModel(eachRecipeObject.getInt(RECIPE_ID)
                        , eachRecipeObject.getString(RECIPE_NAME), eachRecipeObject.getString(RECIPE_IMAGE),
                        eachRecipeObject.getInt(RECIPE_SERVINGS)));

            }
            return bakingModels;


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static ArrayList<IngredientModel> getIngredientsDataFromJson(int recipeId, String JsonString) throws JSONException {

        ArrayList<IngredientModel> ingredientModels = new ArrayList<>();


        JSONArray jsonArray = new JSONArray(JsonString);


        JSONObject eachRecipeObject = jsonArray.getJSONObject(recipeId - 1);

        JSONArray jsonArray1 = eachRecipeObject.getJSONArray(RECIPE_INGREDIENTS);


        for (int j = 0; j < jsonArray1.length(); j++) {
            JSONObject eachRecipeIngredientsObject = jsonArray1.getJSONObject(j);

            ingredientModels.add(new IngredientModel(eachRecipeIngredientsObject.getString(INGREDIENTS_MEASURE)
                    , eachRecipeIngredientsObject.getString(INGREDIENTS_INGREDIENT),
                    eachRecipeIngredientsObject.getInt(INGREDIENTS_QUANTITY)));



        }
        return ingredientModels;
    }
        public static ArrayList<RecipeStepsModel> getRecipeStepsModelsFromJson(int recipeId, String  JsonString)
        {

            ArrayList<RecipeStepsModel> recipeStepsModels = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(JsonString);


                JSONObject eachRecipeObject = jsonArray.getJSONObject(recipeId-1);

                JSONArray jsonArray1 = eachRecipeObject.getJSONArray(RECIPE_STEPS);


                for (int k = 0; k < jsonArray1.length(); k++) {
                    JSONObject eachRecipeStepsObject = jsonArray1.getJSONObject(k);

                    recipeStepsModels.add(new RecipeStepsModel(eachRecipeStepsObject.getInt(STEPS_ID),
                            eachRecipeStepsObject.getString(STEPS_SHORT_DESCRIPTION),
                            eachRecipeStepsObject.getString(STEPS_DESCRIPTION),
                            eachRecipeStepsObject.getString(STEPS_VIDEO_URL),
                            eachRecipeStepsObject.getString(STEPS_THUMBNAIL_URL)));


                }
                return recipeStepsModels;
            }
            catch (JSONException je){
                je.printStackTrace();
                return null;
            }
        }


    }


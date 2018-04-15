package baking.com.baking.Models;

import java.util.ArrayList;



public class BakingTopModel  {

    private ArrayList<BakingModel> bakingModels;
    private ArrayList<IngredientModel> ingredientModels;
    private ArrayList<RecipeStepsModel> recipeStepsModels;

    public BakingTopModel(ArrayList<BakingModel> bakingModels, ArrayList<IngredientModel> ingredientModels, ArrayList<RecipeStepsModel> recipeStepsModels) {
        this.bakingModels = bakingModels;
        this.ingredientModels = ingredientModels;
        this.recipeStepsModels = recipeStepsModels;
    }

    public ArrayList<BakingModel> getBakingModels() {
        return bakingModels;
    }

    public void setBakingModels(ArrayList<BakingModel> bakingModels) {
        this.bakingModels = bakingModels;
    }

    public ArrayList<IngredientModel> getIngredientModels() {
        return ingredientModels;
    }

    public void setIngredientModels(ArrayList<IngredientModel> ingredientModels) {
        this.ingredientModels = ingredientModels;
    }

    public ArrayList<RecipeStepsModel> getRecipeStepsModels() {
        return recipeStepsModels;
    }

    public void setRecipeStepsModels(ArrayList<RecipeStepsModel> recipeStepsModels) {
        this.recipeStepsModels = recipeStepsModels;
    }
}

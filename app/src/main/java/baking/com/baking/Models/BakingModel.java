package baking.com.baking.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class BakingModel implements Parcelable {


    private int recipeId;
    private String recipeName;
    private String imageURL;
    private int servings;

    public BakingModel(int recipeId, String recipeName, String imageURL, int servings) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.imageURL = imageURL;
        this.servings = servings;
    }

        private BakingModel(Parcel in) {
        recipeId = in.readInt();
        recipeName = in.readString();
        imageURL = in.readString();
        servings = in.readInt();

    }

    public static final Creator<BakingModel> CREATOR = new Creator<BakingModel>() {
        @Override
        public BakingModel createFromParcel(Parcel in) {
            return new BakingModel(in);
        }

        @Override
        public BakingModel[] newArray(int size) {
            return new BakingModel[size];
        }
    };

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getServings() {
        return servings;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(recipeId);
        parcel.writeString(recipeName);
        parcel.writeString(imageURL);
        parcel.writeInt(servings);
    }
}

package baking.com.baking.Models;

import android.os.Parcel;
import android.os.Parcelable;


public class IngredientModel implements Parcelable {

    private String measure;
    private String ingredient;
    private int quantity;

    @Override
    public int describeContents() {
        return 0;
    }

    private IngredientModel(Parcel in) {
        measure = in.readString();
        ingredient = in.readString();
        quantity = in.readInt();
    }

    public IngredientModel(String measure, String ingredient, int quantity) {
        this.measure = measure;
        this.ingredient = ingredient;
        this.quantity = quantity;


    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(measure);
        parcel.writeString(ingredient);
        parcel.writeInt(quantity);
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


    public static final Creator<IngredientModel> CREATOR = new Creator<IngredientModel>() {
        @Override
        public IngredientModel createFromParcel(Parcel parcel) {
            return new IngredientModel(parcel);
        }

        @Override
        public IngredientModel[] newArray(int size) {
            return new IngredientModel[size];
        }
    };


}

package baking.com.baking.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import baking.com.baking.Models.IngredientModel;
import baking.com.baking.R;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsAdapterViewHolder> {
    private final ArrayList<IngredientModel> ingredientModels = new ArrayList<>();
    @Override
    public IngredientsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ingredients_layout,parent,false);
        return  new IngredientsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsAdapterViewHolder holder, int position) {
        holder.ingredientName.setText(ingredientModels.get(position).getIngredient());
        holder.ingredientMeasure.setText("Measure : " +ingredientModels.get(position).getMeasure());
        holder.ingredientQuantity.setText("Quantity : " + Integer.toString(ingredientModels.get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return ingredientModels.size();
    }
    public void  addItem(IngredientModel ingredientModel){
        if(ingredientModel!=null){
            ingredientModels.add(ingredientModel);
        }
    }
    public void  addAllItem(ArrayList<IngredientModel> ingredientModels){
        if(ingredientModels!=null){
            this.ingredientModels.addAll(ingredientModels);
        }
    }

    public  class IngredientsAdapterViewHolder extends RecyclerView.ViewHolder{
        private final TextView ingredientName;
        private final TextView ingredientMeasure;
        private final TextView ingredientQuantity;

        public IngredientsAdapterViewHolder(View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientMeasure = itemView.findViewById(R.id.measure);
            ingredientQuantity = itemView.findViewById(R.id.quantity);
        }
    }

}

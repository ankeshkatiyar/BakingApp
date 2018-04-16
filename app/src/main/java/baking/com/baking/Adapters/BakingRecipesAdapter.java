package baking.com.baking.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import baking.com.baking.Models.BakingModel;
import baking.com.baking.R;
import baking.com.baking.RecipeClickListener;


public class BakingRecipesAdapter extends RecyclerView.Adapter<BakingRecipesAdapter.BakingRecipeViewHolder>{

    private ArrayList<BakingModel> bakingModels = new ArrayList<>();
    private final RecipeClickListener recipeClickListener ;
    private Context context;
    public BakingRecipesAdapter(RecipeClickListener recipeClickListener){
        this.bakingModels = bakingModels;
        this.recipeClickListener = recipeClickListener;
    }

    @Override
    public BakingRecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipes_layout, parent,false);
        return new BakingRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BakingRecipeViewHolder holder, int position) {

        holder.recipeServingsTextView.setText("Servings : " + Integer.toString(bakingModels.get(position).getServings()));
       // String url = bakingModels.get(position).getImageURL();
        String url = "https://www.gstatic.com/webp/gallery3/1.png";

        if(!url.isEmpty()){
            holder.recipeNameTextView.setVisibility(View.GONE);
            holder.recipeName.setText(bakingModels.get(position).getRecipeName());
            holder.recipeName.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.VISIBLE);
            Picasso.with(context).load(url).into(holder.imageView);
        }
        else{
            holder.recipeNameTextView.setText(bakingModels.get(position).getRecipeName());
        }


    }

    @Override
    public int getItemCount() {
        return bakingModels.size();
    }
    public void addItem(BakingModel bakingModel){
        if(bakingModel!=null){
            bakingModels.add(bakingModel);
        }
    }
    public void addAllItems(ArrayList<BakingModel> bakingModels){
        if(bakingModels !=null){
            this.bakingModels.addAll(bakingModels);
        }
    }

   public class BakingRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView recipeNameTextView;
        private final TextView recipeServingsTextView;
        private final ImageView imageView;
        private final TextView recipeName;

        @Override
        public void onClick(View view) {
            int recipePosition = getAdapterPosition();
            recipeClickListener.RecipeClicked(bakingModels.get(recipePosition),recipePosition);
        }


        public BakingRecipeViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipe_name);
            recipeServingsTextView = itemView.findViewById(R.id.recipe_servings);
            imageView = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name_1);
            itemView.setOnClickListener(this);
        }
    }


}

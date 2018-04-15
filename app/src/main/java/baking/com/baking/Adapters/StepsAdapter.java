package baking.com.baking.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import baking.com.baking.Models.IngredientModel;
import baking.com.baking.Models.RecipeStepsModel;
import baking.com.baking.R;
import baking.com.baking.RecipeClickListener;
import baking.com.baking.StepsClickListener;

public class StepsAdapter  extends RecyclerView.Adapter<StepsAdapter.StepsAdapterViewHolder>{
    private final ArrayList<RecipeStepsModel> recipeStepsModels = new ArrayList<>();
    private final StepsClickListener stepsClickListener;
    private final ArrayList<Integer> videosWatched ;

    public StepsAdapter(StepsClickListener stepsClickListener , ArrayList<Integer> videosWatched) {
        this.stepsClickListener = stepsClickListener;
        this.videosWatched = videosWatched;
    }

    @Override
    public StepsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.steps_card_layout,parent,false);
        return new StepsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapterViewHolder holder, int position) {

            if(videosWatched.contains(recipeStepsModels.get(position).getStepId())) {

                holder.relativeLayout.setBackgroundResource(R.color.watchedVideo);

                holder.textView.setText(recipeStepsModels.get(position).getShortDescription());
            }
            else{
                holder.textView.setText(recipeStepsModels.get(position).getShortDescription());
            }


    }

    @Override
    public int getItemCount() {
        return recipeStepsModels.size();
    }

    public void  addItem(RecipeStepsModel recipeStepsModel){
        if(recipeStepsModel!=null){
            recipeStepsModels.add(recipeStepsModel);
        }
    }
    public void  addAllItem(ArrayList<RecipeStepsModel> recipeStepsModels){
        if(recipeStepsModels!=null){
            this.recipeStepsModels.addAll(recipeStepsModels);
        }
    }

    public  class StepsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView textView;
        private ImageView imageView;
        private final RelativeLayout relativeLayout;

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            stepsClickListener.StepsClickListener(recipeStepsModels.get(position) , position);


        }

        public StepsAdapterViewHolder(View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.step_name);
            relativeLayout = itemView.findViewById(R.id.mainbox);

            //imageView = (ImageView) itemView.findViewById(R.id.arrow);
            //textView.setOnClickListener(this);
            relativeLayout.setOnClickListener(this);
        }
    }
}

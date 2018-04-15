package baking.com.baking.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import baking.com.baking.MainActivity;
import baking.com.baking.R;


public class IngredientAndStepsContainerFragment extends Fragment {

    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private TabLayout tabLayout;
    private int recipeId;
    private RelativeLayout relativeLayout;
    private Toolbar fragment_toolbar;
    private Toolbar activity_toolbar;
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ingredients_and_steps_container_layout, container, false);
        try {
        position = getArguments().getInt("Position");

        removeActivityToolbar();
        fragment_toolbar = view.findViewById(R.id.ingre_steps_toolbar);
        fragment_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        recipeId = getArguments().getInt("RecipeId");
        FragmentManager fragmentManager = getChildFragmentManager();
        myPagerAdapter = new MyPagerAdapter(fragmentManager);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setTabsFromPagerAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

            TabLayout.Tab tab = tabLayout.getTabAt(position);
            tab.select();
        } catch (NullPointerException npe) {
                npe.printStackTrace();
        }
        return view;

    }

    private void removeActivityToolbar() {
        activity_toolbar = getActivity().findViewById(R.id.activity_toolbar);
        relativeLayout = getActivity().findViewById(R.id.activity_parent);
        relativeLayout.removeView(activity_toolbar);
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        final Bundle bundle;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            bundle = new Bundle();
        }

        @Override
        public Fragment getItem(int position) {

            bundle.putInt("RecipeId", recipeId);

            if (position == 0) {
                IngredientsFragment fragment = new IngredientsFragment();
                fragment.setArguments(bundle);
                return fragment;
            } else {
                MainStepsFragment fragment = new MainStepsFragment();
                fragment.setArguments(bundle);
                return fragment;
            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return IngredientsFragment.INGREDIENTS;
            } else {
                return MainStepsFragment.STEPS;
            }
        }
    }
}

package baking.com.baking;


import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.view.View;
import android.widget.LinearLayout;
import android.app.Fragment;
import android.widget.RelativeLayout;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import baking.com.baking.Fragments.IngredientAndStepsContainerFragment;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class RecipeFragmentClickTest {

    public static final String ING_NAME = "salt";
    public static final String STEP_NAME = "Recipe Introduction";


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity = null;

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResources() {

        mainActivity = mActivityTestRule.getActivity();


    }
    @Test
    public  void testLaunch(){

        RelativeLayout linearLayout = (RelativeLayout) mainActivity.findViewById(R.id.recipe_frag);
        assertNotNull(linearLayout);
        IngredientAndStepsContainerFragment fragment = new IngredientAndStepsContainerFragment();
        mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.recipe_frag,fragment).commitAllowingStateLoss();
        getInstrumentation().waitForIdleSync();
        View view = fragment.getView().findViewById(R.id.ing_steps);
        assertNotNull(view);

    }


    @After
    public void tearDown(){
        mainActivity = null;

    }


}

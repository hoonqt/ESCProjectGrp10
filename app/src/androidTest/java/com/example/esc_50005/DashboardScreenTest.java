package com.example.esc_50005;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.*;
import android.util.Log;
import android.view.View;

import com.example.esc_50005.UI.Dashboard.main.DashboardActivity;
import com.example.esc_50005.UI.Dashboard.professor.ProfessorDashboardFragment;
import com.example.esc_50005.UI.Dashboard.student.StudentDashboardFragment;
import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.Login.LoginFragment;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Created by cindy on 31/3/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DashboardScreenTest {

    LoginFragment loginFragment;
    private Context instrumentationCtx;
    private SharedPreferences preferencesEditor;

    @Rule
    public ActivityTestRule<DashboardActivity> mActivityRule =
            new ActivityTestRule(DashboardActivity.class);


    @Before
    public void init(){

        SharedPreferences.Editor editor = preferencesEditor.edit();
        editor.putString("Username", "jiawen");
        editor.putString("Password", "jiawen");
        editor.putString("UserType", "professor");
        editor.commit();

//        ProfessorDashboardFragment fragment = new ProfessorDashboardFragment();
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.professorDashboardFragment,new ProfessorDashboardFragment()).commit();
//        mActivityRule.getActivity()
//                .getSupportFragmentManager().beginTransaction();
    }
    @Test
    public void loadCourse(){

        // First, scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.recyclerViewDashboardCourses))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,
                        click()));

        // Match the text in an item below the fold and check that it's displayed.
        String itemElementText = "50.005 Computer System Engineering";
        onView(withText(itemElementText)).check(matches(isDisplayed()));


    }
}


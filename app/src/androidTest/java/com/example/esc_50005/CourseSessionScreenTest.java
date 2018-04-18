package com.example.esc_50005;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.esc_50005.UI.Course.FAQ.CourseActivity;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsContract;
import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.Login.LoginFragment;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Created by cindy on 31/3/2018.
 */

public class CourseSessionScreenTest {
    private Context instrumentationCtx;
    private SharedPreferences preferencesEditor;

    @Rule
    public ActivityTestRule<CourseActivity> mActivityRule =
            new ActivityTestRule(CourseActivity.class);

    @Before
    public void init(){
        Context context = getInstrumentation().getTargetContext();
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context);
        //currently testing sessions tab
        Matcher<View> matcher = allOf(withText("Sessions"),
                isDescendantOfA(withId(R.id.tab_layout)));
        onView(matcher).perform(click());
//        onView(withId(R.id.tab_layout)).perform(swipeLeft());
        onView(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withId(R.id.pager)))
                .check(matches(isDisplayed()));
    }
    @Test
    public void addNewSession(){
        onView(withId(R.id.add_sessions)).check(matches(isDisplayed()));
        onView(withId(R.id.add_sessions)).perform(click());
        onView(withText("Add new session")).check(matches(isDisplayed()));
//        onView(withId(R.id.layout_add_session)).check(matches(withHint("Session Id")));
//        onView(withText("Session Id")).perform(typeText("104"));
//        onView(allOf(withClassName(endsWith("Session Id")), withText(is("Session Id")))).perform(replaceText("104"));
//        onView(withText("Submit")).perform(click());

    }
}

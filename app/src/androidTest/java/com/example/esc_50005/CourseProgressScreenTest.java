package com.example.esc_50005;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.esc_50005.UI.Course.FAQ.CourseActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by 1002215 on 2/4/18.
 */

public class CourseProgressScreenTest {

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
        Matcher<View> matcher = allOf(withText("Progress"),
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
        onView(withId(R.id.course_fab)).check(matches(isDisplayed()));
        onView(withId(R.id.course_fab)).perform(click());
        onView(withText("Add new session")).check(matches(isDisplayed()));
        onView(withId(R.id.layout_add_session)).check(matches(withHint("Session Id")));
//        onView(withText("Session Id")).perform(typeText("104"));
//        onView(allOf(withClassName(endsWith("Session Id")), withText(is("Session Id")))).perform(replaceText("104"));
//        onView(withText("Submit")).perform(click());

    }
}

package com.example.esc_50005;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.esc_50005.UI.Session.Main.SessionActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest


public class StudentSessionTest {

    Intent intent;
    SharedPreferences.Editor preferencesEditor;

    @Rule
    public ActivityTestRule<SessionActivity> mActivitytestRule =
            new ActivityTestRule<SessionActivity>(SessionActivity.class);

    @Before
    public void setUp() {
        intent = new Intent();
        Context context = getInstrumentation().getTargetContext();

        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String userType = "student";
        preferencesEditor.putString(mActivitytestRule.getActivity().getString(R.string.user_type),userType);
        preferencesEditor.putString(mActivitytestRule.getActivity().getString(R.string.course_id),"50.005");
        preferencesEditor.putString(mActivitytestRule.getActivity().getString(R.string.session_id),"101");
        preferencesEditor.commit();

        onView(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withId(R.id.pager)))
                .check(matches(isDisplayed()));

        onView(Matchers.allOf(childAtPosition(
                childAtPosition(
                        withId(R.id.tab_layout),
                        0),
                1),isDisplayed())).perform(click());

        onView(withId(R.id.collapsing_toolbar)).perform(swipeUp());
    }

    @Test
    public void attemptQn() {

        onView(withId(R.id.recyclerViewStudentQuiz)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewStudentQuiz)).perform(TestUtils.actionOnItemViewAtPosition(0,R.id.quizname,click()));

        onView(withText("Enter answer")).check(matches(isDisplayed()));
        onView(withText("How did Friday Night Lights end?")).check(matches(isDisplayed()));
        onView(withHint("Answer")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button2)).check(matches(isDisplayed()));

        onView(withHint("Answer")).perform(replaceText("They lost the game."));
        onView(withId(android.R.id.button2)).perform(click());



    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

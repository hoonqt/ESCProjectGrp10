package com.example.esc_50005;


import android.content.Context;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.esc_50005.UI.Session.Main.SessionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfSessionTest {

    @Rule
    public ActivityTestRule<SessionActivity> mActivitytestRule =
            new ActivityTestRule<SessionActivity>(SessionActivity.class);


    @Test
    public void clickHereandThere() {

        onView(withId(R.id.tab_layout)).perform(swipeLeft());
        onView(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withId(R.id.pager)))
                .check(matches(isDisplayed()));

        onView(withId(R.id.tab_layout)).perform(swipeRight());
        onView(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withId(R.id.pager)))
                .check(matches(isDisplayed()));

    }

}

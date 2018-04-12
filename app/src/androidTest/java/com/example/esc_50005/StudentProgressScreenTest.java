package com.example.esc_50005;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.esc_50005.R;
import com.example.esc_50005.UI.Login.LoginActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class StudentProgressScreenTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void studentProgressScreenTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.user_id),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("1001566"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText2.perform(scrollTo(), replaceText("jiawen"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Sign in"),
                        childAtPosition(
                                allOf(withId(R.id.email_login_form),
                                        childAtPosition(
                                                withId(R.id.login_form),
                                                0)),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerViewDashboardCourses),
                        childAtPosition(
                                withId(R.id.courses_swipe),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction tabView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tab_layout),
                                0),
                        2),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.name_list_progress_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name_list_cv),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

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

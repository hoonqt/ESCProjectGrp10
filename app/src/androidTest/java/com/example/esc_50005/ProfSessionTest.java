package com.example.esc_50005;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.Root;
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
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfSessionTest {

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
        String userType = "professor";
        preferencesEditor.putString(mActivitytestRule.getActivity().getString(R.string.user_type),userType);
        preferencesEditor.putString(mActivitytestRule.getActivity().getString(R.string.course_id),"50.005");
        preferencesEditor.putString(mActivitytestRule.getActivity().getString(R.string.session_id),"101");
        preferencesEditor.commit();

        onView(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withId(R.id.pager)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void addQuestion() {
        onView(Matchers.allOf(childAtPosition(
                childAtPosition(
                        withId(R.id.tab_layout),
                        0),
                1),isDisplayed())).perform(click());
        onView(withId(R.id.session_fab)).check(matches(isDisplayed()));
        onView(withId(R.id.session_fab)).perform(click());
        onView(withId(R.id.session_fab)).perform(click());
        onView(withText("Create new activity")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.quizName)).check(matches(isDisplayed()));
        onView(withId(R.id.quizName)).perform(replaceText("Question 3"));
        onView(withId(R.id.qnBubble)).perform(click());
        onView(withId(R.id.submit)).perform(click());

        onView(withText("Create Question 3")).check(matches(isDisplayed()));
        onView(withHint("Question")).perform(replaceText("Which celebrities were ousted because of th #metoo movement?"));
        onView(withId(android.R.id.button2)).perform(click());

    }


    @Test
    public void editQuestion() {

        onView(Matchers.allOf(childAtPosition(
                childAtPosition(
                        withId(R.id.tab_layout),
                        0),
                1),isDisplayed())).perform(click());

        onView(withId(R.id.recyclerViewProfQuiz)).perform(TestUtils.actionOnItemViewAtPosition(0,
                R.id.moreinfobutton,click()));

        onView(withText("Edit")).inRoot(isPopupWindow()).perform(click());
        onView(withText("Edit question")).check(matches(isDisplayed()));
        onView(withText("How many members are there in the US senate?")).perform(replaceText("How did Friday Night Lights end?"));
        onView(withId(android.R.id.button2)).perform(click());
        
    }

    @Test
    public void addQuiz() {
        onView(Matchers.allOf(childAtPosition(
                childAtPosition(
                        withId(R.id.tab_layout),
                        0),
                1),isDisplayed())).perform(click());
        onView(withId(R.id.session_fab)).check(matches(isDisplayed()));
        onView(withId(R.id.session_fab)).perform(click());
        onView(withId(R.id.session_fab)).perform(click());
        onView(withText("Create new activity")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withId(R.id.quizName)).check(matches(isDisplayed()));
        onView(withId(R.id.quizName)).perform(replaceText("Quiz 2"));
        onView(withId(R.id.quizBubble)).perform(click());
        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.collapsing_toolbar)).perform(swipeUp());

        onView(withId(R.id.addbutton)).check(matches(isDisplayed()));
        onView(withId(R.id.addbutton)).perform(click());

        onView(withId(R.id.questionBox)).check(matches(isDisplayed()));
        onView(withId(R.id.option1ans)).check(matches(isDisplayed()));
        onView(withId(R.id.option2ans)).check(matches(isDisplayed()));
        onView(withId(R.id.option3ans)).check(matches(isDisplayed()));
        onView(withId(R.id.option4ans)).check(matches(isDisplayed()));

        onView(withId(R.id.questionBox)).perform(replaceText("How many female senators are there in the US Senate?"));
        onView(withId(R.id.option1ans)).perform(replaceText("21"));
        onView(withId(R.id.option2ans)).perform(replaceText("22"));
        onView(withId(R.id.option3ans)).perform(replaceText("23"));
        onView(withId(R.id.option4ans)).perform(replaceText("24"));

        onView(withId(R.id.option3)).perform(click());

        onView(withId(R.id.submitbtn)).perform(click());

        onView(withId(R.id.recyclerViewQuizList)).check(matches(isDisplayed()));


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


    public static Matcher<Root> isPopupWindow() {
        return isPlatformPopup();
    }



}


package com.example.esc_50005.UI.Welcome;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.esc_50005.R;

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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void welcomeActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.sign_in_button), withText("Sign In"),
                        childAtPosition(
                                allOf(withId(R.id.signupFragment),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0)));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("corgi101"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("corgihello"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register"),
                        childAtPosition(
                                allOf(withId(R.id.email_login_form),
                                        childAtPosition(
                                                withId(R.id.login_form),
                                                0)),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.addNewCourse), withText("+Add"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout1),
                                        childAtPosition(
                                                withId(R.id.studentDashboardFragment),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(android.R.id.custom),
                                0),
                        0),
                        isDisplayed()));
        editText.perform(replaceText("50.008"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button2), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton4.perform(scrollTo(), click());

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
//||||||| merged common ancestors
//=======
//package com.example.esc_50005.UI.Welcome;
//
//
//import android.support.test.espresso.ViewInteraction;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.test.suitebuilder.annotation.LargeTest;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import com.example.esc_50005.R;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.replaceText;
//import static android.support.test.espresso.action.ViewActions.scrollTo;
//import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//import static org.hamcrest.Matchers.is;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class WelcomeActivityTest {
//
//    @Rule
//    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);
//
//    @Test
//    public void welcomeActivityTest() {
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.sign_in_button), withText("Sign In"),
//                        childAtPosition(
//                                allOf(withId(R.id.signupFragment),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.LinearLayout")),
//                                                0)),
//                                0),
//                        isDisplayed()));
//        appCompatButton.perform(click());
//
//        ViewInteraction appCompatAutoCompleteTextView = onView(
//                allOf(withId(R.id.email),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.support.design.widget.TextInputLayout")),
//                                        0),
//                                0)));
//        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("jiawen"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.password),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.support.design.widget.TextInputLayout")),
//                                        0),
//                                0)));
//        appCompatEditText.perform(scrollTo(), replaceText("jiawen"), closeSoftKeyboard());
//
//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register"),
//                        childAtPosition(
//                                allOf(withId(R.id.email_login_form),
//                                        childAtPosition(
//                                                withId(R.id.login_form),
//                                                0)),
//                                3)));
//        appCompatButton2.perform(scrollTo(), click());
//
//        ViewInteraction recyclerView = onView(
//                allOf(withId(R.id.recyclerViewDashboardCourses),
//                        childAtPosition(
//                                withId(R.id.studentDashboardFragment),
//                                0)));
//        recyclerView.perform(actionOnItemAtPosition(0, click()));
//
//        ViewInteraction tabView = onView(
//                allOf(childAtPosition(
//                        childAtPosition(
//                                withId(R.id.tab_layout),
//                                0),
//                        2),
//                        isDisplayed()));
//        tabView.perform(click());
//
//        ViewInteraction recyclerView2 = onView(
//                allOf(withId(R.id.recyclerViewDashboardCourses),
//                        childAtPosition(
//                                withId(R.id.studentDashboardFragment),
//                                0)));
//        recyclerView2.perform(actionOnItemAtPosition(0, click()));
//
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
//>>>>>>> 2faa965d2f17aea454e76e9add14b8fb55db4627

package com.example.esc_50005;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.esc_50005.UI.Course.FAQ.CourseActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkArgument;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Otter on 3/4/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FaqScreenTest {

    private SharedPreferences preferencesEditor;

    @Rule
    public ActivityTestRule<CourseActivity> mTasksActivityTestRule =
            new ActivityTestRule<CourseActivity>(CourseActivity.class);

    @Before
    public void init(){
        Context context = getInstrumentation().getTargetContext();
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context);
        //currently testing sessions tab
        Matcher<View> matcher = allOf(withText("FAQ"),
                isDescendantOfA(withId(R.id.tab_layout)));
        onView(matcher).perform(click());
    }

    @Test
    public void clickAddFaqFab_OpensEditFaqDialog() {
        // Click on the add task button
        onView(withId(R.id.course_fab)).perform(click());
        onView(withId(R.id.edit_faq_tv_title)).check(matches(isDisplayed()));
    }

    @Test
    public void addFaq() {
        // Click on the add faq button
        onView(withId(R.id.course_fab)).perform(click());

        // Add faq question and answer
        onView(withId(R.id.edit_faq_et_question)).perform(typeText("What is the colour of the sky?"),
                closeSoftKeyboard()); // Type new task title
        onView(withId(R.id.edit_faq_et_answer)).perform(typeText("The sky is blue."),
                closeSoftKeyboard()); // Type new task description and close the keyboard

        // Save the faq
        onView(withId(R.id.edit_faq_btn_save)).perform(click());

//        try {
//            Thread.sleep(2000);
//            swipeDown();
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        onView(withId(R.id.faq_cv))
//                .check(matches(atPosition(2, hasDescendant(withText("What is the colour of the sky?")))));
//        onView(withItemText("What is the colour of the sky?")).check(matches(isDisplayed()));
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    private Matcher<View> withItemText(final String itemText) {
        checkArgument(!TextUtils.isEmpty(itemText), "itemText cannot be null or empty");
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View item) {
                return allOf(
                        isDescendantOfA(isAssignableFrom(CardView.class)),
                        withText(itemText)).matches(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is isDescendantOfA RV with text " + itemText);
            }
        };
    }
}

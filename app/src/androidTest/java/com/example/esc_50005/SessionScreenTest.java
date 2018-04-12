//package com.example.esc_50005;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//import android.support.test.espresso.matcher.ViewMatchers;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.text.TextUtils;
//import android.util.Log;
//import android.widget.ListView;
//
//import com.amazonaws.mobile.client.AWSMobileClient;
//import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsContract;
//import com.example.esc_50005.UI.Course.FAQ.session.student.StudentSessionsFragment;
//import com.example.esc_50005.UI.Session.Main.SessionActivity;
//
//import org.hamcrest.Matcher;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.support.test.InstrumentationRegistry.getInstrumentation;
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.swipeLeft;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.core.AllOf.allOf;
//
///**
// * Created by cindy on 31/3/2018.
// */
//
//@RunWith(AndroidJUnit4.class)
//
//public class SessionScreenTest {
//
//    private final static String TITLE1 = "TITLE1";
//
//    private final static String DESCRIPTION = "DESCR";
//
//    private final static String TITLE2 = "TITLE2";
//    private Context instrumentationCtx;
//    private SharedPreferences preferencesEditor;
//
//    /**
//     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
//     * <p>
//     * Rules are interceptors which are executed for each test method and are important building
//     * blocks of Junit tests.
//     */
//    @Rule
//    public ActivityTestRule<SessionActivity> mActivityRule = new ActivityTestRule<>(
//            SessionActivity.class);
//
//    @Before
//    public void init(){
////        Context context = getInstrumentation().getTargetContext();
////        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context);
////        SharedPreferences.Editor editor = preferencesEditor.edit();
////        editor.putString("Username", "cindy");
////        editor.putString("Password", "cindyhello");
////        editor.putString("UserType", "student");
////        editor.commit();
//
////        Matcher<SessionsContract.View> matcher = allOf(withText("TAB TITLE"),
////                isDescendantOfA(withId(R.id.customTab)));
////        onView(matcher).perform(click());
//        onView(withId(R.id.tab_layout)).perform(swipeLeft());
//        onView(allOf(
//                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
//                withId(R.id.pager)))
//                .check(matches(isDisplayed()));
//
//        StudentSessionsFragment fragment = new StudentSessionsFragment();
//        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction()
//                .commit();
//    }
//
//    @Test
//    public void TestAutoComplete(){
//        onView(withId(R.id.add_new_session))        // withId(R.id.my_view) is a ViewMatcher
//                .perform(click())               // click() is a ViewAction
//                .check(matches(isDisplayed())); // matches(isDisplayed()) is a ViewAssertion
//    }
//
//
//
//    /**
//     * A custom {@link Matcher} which matches an item in a {@link ListView} by its text.
//     * <p>
//     * View constraints:
//     * <ul>
//     * <li>View must be a child of a {@link ListView}
//     * <ul>
//     *
//     * @param itemText the text to match
//     * @return Matcher that matches text in the given view
//     */
////    private Matcher<SessionsContract.View> withItemText(final String itemText) {
////        checkArgument(!TextUtils.isEmpty(itemText), "itemText cannot be null or empty");
////        return new TypeSafeMatcher<View>() {
////            @Override
////            public boolean matchesSafely(View item) {
////                return allOf(
////                        isDescendantOfA(isAssignableFrom(ListView.class)),
////                        withText(itemText)).matches(item);
////            }
////
////            @Override
////            public void describeTo(Description description) {
////                description.appendText("is isDescendantOfA LV with text " + itemText);
////            }
////        };
////    }
////
////    @Test
////    public void clickAddTaskButton_opensAddTaskUi() {
////        // Click on the add task button
////        onView(withId(R.id.fab_add_task)).perform(click());
////
////        // Check if the add task screen is displayed
////        onView(withId(R.id.add_task_title)).check(matches(isDisplayed()));
////    }
////
////    @Test
////    public void editTask() throws Exception {
////        // First add a task
////        createTask(TITLE1, DESCRIPTION);
////
////        // Click on the task on the list
////        onView(withText(TITLE1)).perform(click());
////
////        // Click on the edit task button
////        onView(withId(R.id.fab_edit_task)).perform(click());
////
////        String editTaskTitle = TITLE2;
////        String editTaskDescription = "New Description";
////
////        // Edit task title and description
////        onView(withId(R.id.add_task_title))
////                .perform(replaceText(editTaskTitle), closeSoftKeyboard()); // Type new task title
////        onView(withId(R.id.add_task_description)).perform(replaceText(editTaskDescription),
////                closeSoftKeyboard()); // Type new task description and close the keyboard
////
////        // Save the task
////        onView(withId(R.id.fab_edit_task_done)).perform(click());
////
////        // Verify task is displayed on screen in the task list.
////        onView(withItemText(editTaskTitle)).check(matches(isDisplayed()));
////
////        // Verify previous task is not displayed
////        onView(withItemText(TITLE1)).check(doesNotExist());
////    }
////
////    @Test
////    public void changeText_sameActivity() {
////        // Type text and then press the button.
////        onView(withId(R.id.editTextUserInput))
////                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
////        onView(withId(R.id.changeTextBt)).perform(click());
////
////        // Check that the text was changed.
////        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
////    }
//}

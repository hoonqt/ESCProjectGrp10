//package com.example.esc_50005;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//import android.support.test.espresso.matcher.ViewMatchers;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.test.suitebuilder.annotation.LargeTest;
//import android.view.View;
//
//import com.example.esc_50005.UI.Course.FAQ.CourseActivity;
//import com.example.esc_50005.UI.Course.FAQ.ProgressStudentFragment;
//import com.example.esc_50005.UI.Login.LoginActivity;
//import com.example.esc_50005.UI.Login.LoginFragment;
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
//import static android.support.test.espresso.action.ViewActions.typeText;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
//import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.core.AllOf.allOf;
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by 1002215 on 4/4/18.
// */
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class ProgressScreenTest {
//
//    ProgressStudentFragment progressFragment;
//    private Context instrumentationCtx;
//    private SharedPreferences preferencesEditor;
//
//    @Rule
//    public ActivityTestRule<CourseActivity> mActivityRule =
//            new ActivityTestRule<CourseActivity>(CourseActivity.class);
//
//    @Before
//    public void init(){
//        Context context = getInstrumentation().getTargetContext();
//        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context);
//        //currently testing sessions tab
//        Matcher<View> matcher = allOf(withText("Progress"),
//                isDescendantOfA(withId(R.id.tab_layout)));
//        onView(matcher).perform(click());
//    }
//    @Test
//    public void testVisibility(){
//        //ensures that the relevant text box and radio button are present
//        onView(withId(R.id.title)).check(matches(isDisplayed()));
//        onView(withId(R.id.chart1)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void passAttemptLogin(){
//        //a test to pass the login
//        onView(withId(R.id.email)).perform(typeText("cindy"));
//        onView(withId(R.id.password)).perform(typeText("cindyhello"));
//        onView(withId(R.id.student))
//                .perform(click());
//
//        onView(withId(R.id.student))
//                .check(matches(isChecked()));
//        onView(withId(R.id.login_button)).perform(click());
//
//        String testUsername="cindy";
//        String username=preferencesEditor.getString("Username","");
//
//        assertEquals(username, testUsername);
//    }
//
//    @Test
//    public void failAttemptLogin(){
//        //a test to fail the login
//        onView(withId(R.id.email)).perform(typeText("cindy"));
//        onView(withId(R.id.password)).perform(typeText("cindy"));
//        onView(withId(R.id.student))
//                .perform(click());
//
//        onView(withId(R.id.student))
//                .check(matches(isChecked()));
//        onView(withId(R.id.login_button)).perform(click());
//
//        String testUsername="cindy";
//        String username=preferencesEditor.getString("Username","");
//
//        assertEquals(username, testUsername);
//    }
//}
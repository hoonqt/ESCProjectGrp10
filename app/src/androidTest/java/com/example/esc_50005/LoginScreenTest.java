package com.example.esc_50005;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.matcher.PreferenceMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.esc_50005.UI.Course.FAQ.session.student.StudentSessionsFragment;
import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.Login.LoginFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginScreenTest {

    LoginFragment loginFragment;
    private Context instrumentationCtx;
    private SharedPreferences preferencesEditor;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);

    @Before
    public void init(){
        Context context = getInstrumentation().getTargetContext();
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context);
        LoginFragment fragment = new LoginFragment();
        mActivityRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }
    @Test
    public void testVisibility(){
        //ensures that the relevant text box and radio button are present
        onView(withId(R.id.user_id)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
        onView(withId(R.id.professorOrStudent)).check(matches(isDisplayed()));
    }

    @Test
    public void passAttemptLogin(){
        //a test to pass the login
        onView(withId(R.id.user_id)).perform(typeText("1001792"));
        onView(withId(R.id.password)).perform(typeText("cindy"));
        onView(withId(R.id.student))
                .perform(click());

        onView(withId(R.id.student))
                .check(matches(isChecked()));

    }

    @Test
    public void failAttemptLogin(){
        //a test to fail the login
        onView(withId(R.id.user_id)).perform(typeText("1001792"));
        onView(withId(R.id.password)).perform(typeText("cindy2222"));
        onView(withId(R.id.student))
                .perform(click());

        onView(withId(R.id.student))
                .check(matches(isChecked()));
    }
}

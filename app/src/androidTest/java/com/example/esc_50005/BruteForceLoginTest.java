package com.example.esc_50005;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.Login.LoginFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class BruteForceLoginTest {

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
    public void bruteForceLogin(){
        ArrayList<String> passwords = new ArrayList<>();
        passwords.add("hello");
        passwords.add("thanks");
        passwords.add("12345");
        passwords.add("@#$%^");
        passwords.add("    ");
        passwords.add("?123abc");
        for (int i = 0; i < passwords.size(); i++) {
            onView(withId(R.id.email)).perform(typeText("corgi101"));
            onView(withId(R.id.password)).perform(typeText(passwords.get(i)));
            onView(withId(R.id.login_button)).perform(click());
        }
    }
}

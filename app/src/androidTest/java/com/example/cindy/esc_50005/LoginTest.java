package com.example.cindy.esc_50005;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.cindy.esc_50005.UI.Login.LoginActivity;
import com.example.cindy.esc_50005.UI.Login.LoginFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    LoginFragment loginFragment;
    private Context instrumentationCtx;
    private String username;
    private String password;
    private String userType;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);

    @Before
    public void init(){
        mActivityRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }
    @Test
    public void TestAutoComplete(){
        onView(withId(R.id.email)).check(matches(isDisplayed()));
    }

//    @Before
//    public void runBeforeEachTest()
//    {
//        loginFragment = new LoginFragment();
//        loginFragment.attemptLogin();
//    }
//
//    @Test
//    public void test4failure () {
//        loginFragment.username="cindyh";
//        loginFragment.password="cindyhello";
//        loginFragment.userType="student";
//        assertTrue (loginFragment.attemptLogin());
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void test4error () {
//        loginFragment.username="cindyrr";
//        loginFragment.password="cindyhello";
//        loginFragment.userType="student";
//        assertTrue (loginFragment.attemptLogin());
//    }
//
//    @Test
//    public void test4pass () {//loop once
//        loginFragment.username="cindy";
//        loginFragment.password="cindyhello";
//        loginFragment.userType="student";
//        assertTrue (loginFragment.attemptLogin());
//    }
}

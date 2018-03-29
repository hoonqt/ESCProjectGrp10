package com.example.esc_50005;

import android.content.Context;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.Login.LoginFragment;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;


@RunWith(JUnit4.class)
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

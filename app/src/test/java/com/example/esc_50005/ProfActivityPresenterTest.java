package com.example.esc_50005;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.Database.Quizstuff.Injection;
import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.ProfSession.Contracts.QuizProfContract;
import com.example.esc_50005.UI.ProfSession.Presenters.*;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link ActivityProfPresenter}
 */


public class ProfActivityPresenterTest {

    private static ArrayList<QuizQuestions2DO> questionData;

    @Mock
    private SessionActivity mSessionActivity;

    @Mock
    private QuizProfContract.View mActivityView;

    @Mock
    private QuizRemoteDataSource mActivityRepository;

    @Mock
    private Context context;

    private ActivityProfPresenter mActivityPresenter;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        context = new MockContext();
        context = mSessionActivity.getApplicationContext();

    }

    @Test
    public void createPresenter_setsPresenterToView() {
        mActivityPresenter = new ActivityProfPresenter(mActivityView,context);
        verify(mActivityView).setPresenter(mActivityPresenter);
    }


}

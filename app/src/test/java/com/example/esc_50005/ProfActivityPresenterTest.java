package com.example.esc_50005;

import android.content.Context;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.Session.Professor.Contracts.QuizProfContract;
import com.example.esc_50005.UI.Session.Professor.Presenters.*;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
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
        context = mock(SessionActivity.class);

    }

    @Test
    public void createPresenter_setsPresenterToView() {
        AWSMobileClient.getInstance().initialize(context).execute();
        mActivityPresenter = new ActivityProfPresenter(mActivityView,context.getApplicationContext());
        verify(mActivityView).setPresenter(mActivityPresenter);
    }


}

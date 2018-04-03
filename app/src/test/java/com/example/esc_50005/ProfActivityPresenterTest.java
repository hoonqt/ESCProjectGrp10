package com.example.esc_50005;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.Login.LoginActivity;
import com.example.esc_50005.UI.ProfSession.Contracts.QuizProfContract;
import com.example.esc_50005.UI.ProfSession.Presenters.*;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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
    final Context context = Mockito.mock(Context.class);



    private ActivityProfPresenter mActivityPresenter;

    @Before
    public void setupActivityPresenter() {
        mActivityPresenter = new ActivityProfPresenter(mActivityView,context);
        mSessionActivity = new SessionActivity();

    }

    @Test
    public void createPresenter_setsPresenterToView() {
        mActivityPresenter = new ActivityProfPresenter(mActivityView,context);

        verify(mActivityView).setPresenter(mActivityPresenter);
    }


}

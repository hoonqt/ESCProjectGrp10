//package com.example.esc_50005;
//
//
//import android.content.Context;
//
//import com.amazonaws.mobile.client.AWSMobileClient;
//import com.example.esc_50005.UI.DataAdder.AddDatahere;
//import com.example.esc_50005.UI.Session.Professor.Contracts.QuizProfContract;
//import com.example.esc_50005.UI.Session.Professor.Presenters.ActivityProfPresenter;
//import com.example.esc_50005.UI.Session.Main.SessionActivity;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class SessionQnsTest {
//
//
//    @Mock
//    private QuizProfContract.View mQuizView;
//
//    @Mock
//    private Context mockApplicationContext;
//
//    @Mock
//    private SessionActivity mockSessionActivity;
//
//    private ActivityProfPresenter mProfPresenter;
//
//    private AddDatahere sessionActivity;
//
//    @Before
//    public void setupQuizPresenter() {
//        MockitoAnnotations.initMocks(SessionQnsTest.class);
//        AWSMobileClient.getInstance().initialize(sessionActivity).execute();
//
//    }
//
//
////    @Test
////    public void createPresenter() {
////        mProfPresenter = new ActivityProfPresenter(mQuizView);
////        verify(mQuizView).setPresenter(mProfPresenter);
////
////    }
//
//    @Test
//    public void createPresenter() {
//        mProfPresenter = new ActivityProfPresenter(mQuizView);
//        verify(mQuizView).setPresenter(mProfPresenter);
//
//    }
//
//    @Test
//    public void createPresenter() {
//        mProfPresenter = new ActivityProfPresenter(mQuizView,sessionActivity);
//        verify(mQuizView).setPresenter(mProfPresenter);
//
//    }
//
//
//
//}

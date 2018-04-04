package com.example.esc_50005;

/**
 * Created by 1002215 on 3/4/18.
 */


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.esc_50005.Database.Progress.QuizScores4DO;
import com.example.esc_50005.UI.Course.FAQ.FaqContract;
import com.example.esc_50005.UI.Course.FAQ.FaqPresenter;
import com.example.esc_50005.UI.Course.FAQ.ProgressContract;
import com.example.esc_50005.UI.Course.FAQ.ProgressPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tan_j on 14/3/2018.
 */

/**
 * Unit tests for the implementation of {@link ProgressPresenter}
 */
public class ProgressPresenterTest {

    private static ArrayList<QuizScores4DO> quiz;

    @Mock
    private ProgressContract.View mProgressView;

    @Mock
    private ProgressRemoteDataSource mProgressRepository;

//    @Captor
//    private ArgumentCaptor<Faq> captor;
//
//    @Captor
//    private ArgumentCaptor<ArrayList<Faq>> showFaqArgumentCaptor;


    private ProgressPresenter mProgressPresenter;

    @Before
    public void setupProgressPresenter() {
        MockitoAnnotations.initMocks(this);
        mProgressPresenter = new ProgressPresenter(mProgressRepository, mProgressView);

        quiz = new ArrayList<QuizScores4DO>();
        QuizScores4DO quiz1 = new QuizScores4DO();
        quiz1.setCourseID("50.004");
        quiz1.setStudentIDSessionID("1002212Session1");
        quiz1.setName("Adam Liaw");
        quiz1.setQuizName("Quiz 1");
        quiz1.setScore(5.0);
        quiz.add(quiz1);
        QuizScores4DO quiz2 = new QuizScores4DO();
        quiz2.setCourseID("50.004");
        quiz2.setStudentIDSessionID("1002215Session3");
        quiz2.setName("John Tan");
        quiz2.setQuizName("Quiz 1");
        quiz2.setScore(3.0);
        quiz.add(quiz2);
        QuizScores4DO quiz3 = new QuizScores4DO();
        quiz3.setCourseID("50.005");
        quiz3.setStudentIDSessionID("1002212Session1");
        quiz3.setName("Adam Liaw");
        quiz3.setQuizName("Quiz 1");
        quiz3.setScore(3.0);
        quiz.add(quiz3);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mProgressPresenter = new ProgressPresenter(mProgressRepository, mProgressView);

        // Then the presenter is set to the view
        verify(mProgressView).setPresenter(mProgressPresenter);
    }

//    @Test
//    public void clickOnUpvoteBtn_saveFaqInRepository() {
//        // Given a stubbed faq
//        Faq faq = new Faq();
//        faq.setCourseId("50003");
//        faq.setQuestionId("103");
//        faq.setQuestion("Why is the sky white?");
//        faq.setAnswer("Because its not red");
//        faq.setAuthor("Jonathan");
//        faq.setUsersVoted(new ArrayList<String>());
//
//        // When upvote faq
//        mFaqPresenter.upvoteFaq(faq);
//
////         Then faq is saved
//        verify(mFaqPresenter.mFaqRepository).saveFaq(any(Faq.class));
//    }
//
//    @Test
//    public void secondClickOnUpvoteBtn_saveFaqInRepository() {
//        // Given a stubbed faq
//        Faq faq = new Faq();
//        faq.setCourseId("50003");
//        faq.setQuestionId("103");
//        faq.setQuestion("Why is the sky white?");
//        faq.setAnswer("Because its not red");
//        faq.setAuthor("Jonathan");
//        ArrayList<String> usersVoted = new ArrayList<String>();
//        usersVoted.add("1001688");
//        faq.setUsersVoted(usersVoted);
//
//        // When upvote faq on second click
//        mFaqPresenter.downvoteFaq(faq);
//
////         Then faq is saved
//        verify(mFaqPresenter.mFaqRepository).saveFaq(any(Faq.class));
//    }

    @Test
    public void loadScoresFromRepository() {
//         Given an initialized FaqPresenter with initialized faq
//         When loading of faq is requested
        mProgressPresenter.loadScores();
        verify(mProgressRepository).getScores("50.004","1002212");
    }

    @Test
    public void processScoresFromRepositoryAndLoadIntoView() {
        ArrayList<QuizScores4DO> result = quiz;
        ArrayList<Double> scoreList = new ArrayList<>();
        scoreList.add(5.0);
        scoreList.add(3.0);
        scoreList.add(3.0);
        mProgressPresenter.processScores(result);
        verify(mProgressView).showProgress(scoreList);
    }



}

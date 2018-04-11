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
 * Unit tests for the implementation of {@link ProgressPresenter}
 */
public class StudentProgressPresenterTest {

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
        mProgressPresenter.setCourseId("50.005");
        mProgressPresenter.setStudentId("1002212");

        quiz = new ArrayList<QuizScores4DO>();
        QuizScores4DO quiz1 = new QuizScores4DO();
        quiz1.setCourseID("50.005");
        quiz1.setStudentIDSessionID("1002212Session1");
        quiz1.setName("Adam Liaw");
        quiz1.setQuizName("Quiz 1");
        quiz1.setScore(5.0);
        quiz.add(quiz1);
        QuizScores4DO quiz2 = new QuizScores4DO();
        quiz2.setCourseID("50.005");
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

    @Test
    public void loadScoresFromRepository() {
        mProgressPresenter.loadScores();
        verify(mProgressRepository).getScores("50.005", "1002212");

    }

    @Test
    public void processScoresFromRepositoryAndLoadIntoView() {
        ArrayList<QuizScores4DO> result = quiz;
        ArrayList<Double> scoreList = new ArrayList<>();
        for(int i=0; i<result.size();i++){
            scoreList.add(result.get(i).getScore());
        }
        mProgressPresenter.processScores(result);
        verify(mProgressView).showProgress(scoreList);
    }



}

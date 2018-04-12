package com.example.esc_50005;

/**
 * Created by 1002215 on 11/4/18.
 */

import com.example.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.esc_50005.Database.Progress.QuizScores4DO;
import com.example.esc_50005.UI.Course.FAQ.NameListPresenter;
import com.example.esc_50005.UI.Course.FAQ.ProgressContract;
import com.example.esc_50005.UI.Course.FAQ.ProgressPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link com.example.esc_50005.UI.Course.FAQ.NameListPresenter}
 */
public class NameListPresenterTest {

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


    private NameListPresenter mNameListPresenter;

    @Before
    public void setupProgressPresenter() {
        MockitoAnnotations.initMocks(this);
        mNameListPresenter = new NameListPresenter(mProgressRepository,mProgressView);
        mNameListPresenter.setCourseId("50.005");
        mNameListPresenter.setStudentId("1002212");

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
        quiz3.setStudentIDSessionID("1002216Session1");
        quiz3.setName("Timothy Tan");
        quiz3.setQuizName("Quiz 1");
        quiz3.setScore(3.0);
        quiz.add(quiz3);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mNameListPresenter = new NameListPresenter(mProgressRepository,mProgressView);

        // Then the presenter is set to the view
        verify(mProgressView).setPresenter(mNameListPresenter);
    }

    @Test
    public void loadNamesFromRepository() {
        mNameListPresenter.loadNames();
        verify(mProgressRepository).getNames("50.005");

    }

    @Test
    public void processNamesFromRepositoryAndLoadIntoView() {
        ArrayList<QuizScores4DO> result = quiz;
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> studentId = new ArrayList<>();
        ArrayList<Double> avg = new ArrayList<>();
        for(int i=0; i<result.size();i++){
            nameList.add(result.get(i).getName());
            studentId.add(result.get(i).getStudentIDSessionID());
            avg.add(0.0);

        }

        mNameListPresenter.processNames(result);
        verify(mProgressView).showNames(nameList,studentId,avg);
    }





}


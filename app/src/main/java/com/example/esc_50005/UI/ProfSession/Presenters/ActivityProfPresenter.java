package com.example.esc_50005.UI.ProfSession.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.ProfSession.Contracts.QuizProfContract;

import java.util.ArrayList;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ActivityProfPresenter implements QuizProfContract.Presenter{

    private final QuizProfContract.View mQuizProfView;
    private QuizRemoteDataSource mQuizQuestionsRepository;
    private ArrayList<QuizQuestions2DO> questionData;

    SharedPreferences sharedPreferences;

    String courseCode;
    String sessionID;

    public ActivityProfPresenter(@NonNull QuizProfContract.View quizProfView,Context context) {

        mQuizQuestionsRepository = new QuizRemoteDataSource();
        mQuizProfView = checkNotNull(quizProfView,"Quiz not null");
        mQuizProfView.setPresenter(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        courseCode = sharedPreferences.getString("CurrentCourseActivity", null).split(" ")[0];
        sessionID = sharedPreferences.getString("SessionSelected",null).split("-")[1].trim();

    }

    @Override
    public void start() {

        loadQuizes(courseCode,sessionID);

    }

    @Override
    public void loadQuizes(String subjectCode, String sessionCode) {

        questionData = mQuizQuestionsRepository.getQuestions(subjectCode, sessionCode);
        mQuizProfView.showQuizes(questionData);

    }

    @Override
    public void addNewQuiz(QuizQuestions2DO input) {
        mQuizQuestionsRepository.putQuestion(input);
    }

    @Override
    public void addNewQuiz(String subjCode, String sessionCode, String quizName,String question, String correctAns, ArrayList<String> options) {


    }

    @Override
    public void processEmptyQuiz() {

    }

    public ArrayList<QuizQuestions2DO> getQuestionData(String subjectCode, String sessionCode) {
        loadQuizes(subjectCode, sessionCode);
        return questionData;
    }

    public ArrayList<QuizQuestions2DO> getStoredData() {
        return questionData;
    }
}
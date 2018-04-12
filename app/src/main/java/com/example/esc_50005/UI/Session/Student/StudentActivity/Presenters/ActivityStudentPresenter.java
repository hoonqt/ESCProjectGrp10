package com.example.esc_50005.UI.Session.Student.StudentActivity.Presenters;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.Session.Student.StudentActivity.Contracts.QuizStudentContract;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hoonqt on 30/3/18.
 */

public class ActivityStudentPresenter implements QuizStudentContract.Presenter {

    private final QuizStudentContract.View mQuizStudentView;
    private QuizRemoteDataSource mQuizQuestionsRepository;
    private ArrayList<QuizQuestions2DO> questionData;

    private String courseCode;
    private String sessionID;

    public ActivityStudentPresenter(QuizStudentContract.View fragment) {

        mQuizQuestionsRepository = new QuizRemoteDataSource();
        mQuizStudentView = checkNotNull(fragment,"Quiz not null");
        mQuizStudentView.setPresenter(this);


    }

    @Override
    public void start() {

        loadQuizes("50.005","101","Quiz 1");
    }



    @Override
    public void loadQuizes(String subjectCode, String sessionCode, String quizName) {
        questionData = mQuizQuestionsRepository.getOneQuestion(subjectCode,sessionCode,quizName);
        mQuizStudentView.showQuizes(questionData);
        Log.i("Size of data:", Integer.toString(questionData.size()));
    }

    @Override
    public void processEmptyQuiz() {

    }


}

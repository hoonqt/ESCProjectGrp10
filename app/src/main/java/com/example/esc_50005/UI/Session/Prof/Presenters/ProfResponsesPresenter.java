package com.example.esc_50005.UI.Session.Prof.Presenters;

import android.support.annotation.NonNull;

import com.example.esc_50005.Database.QuizAnswers.QuizAnswersDO;
import com.example.esc_50005.Database.QuizAnswers.QuizAnswersRemoteDataSource;
import com.example.esc_50005.Log;
import com.example.esc_50005.UI.Session.Prof.Contracts.ResponseProfContract;

import java.util.ArrayList;

import static android.support.v4.util.Preconditions.checkNotNull;

public class ProfResponsesPresenter implements ResponseProfContract.Presenter{

    private final ResponseProfContract.View ResponsesProfView;
    private QuizAnswersRemoteDataSource mQuizAnswersData;
    private ArrayList<QuizAnswersDO> responses;

    public ProfResponsesPresenter(@NonNull ResponseProfContract.View responsesProfView) {

        mQuizAnswersData = new QuizAnswersRemoteDataSource();
        ResponsesProfView = checkNotNull(responsesProfView);
        ResponsesProfView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadResponses(String subjectCode, String sessionCode, String qnName) {


        responses = mQuizAnswersData.getQuestions(subjectCode+sessionCode,qnName);

        Log.i("Size: ", responses.toString());
        if (responses == null) {

        }

        else ResponsesProfView.showResponses(responses);
    }

    @Override
    public void processEmptyQuiz() {

    }
}

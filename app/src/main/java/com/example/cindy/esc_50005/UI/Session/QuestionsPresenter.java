package com.example.cindy.esc_50005.UI.Session;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class QuestionsPresenter implements QuestionsContract.Presenter {

    private final QuestionsContract.View mSessionQuestionView;
    private SessionQuestionsRemoteDataSource mSessionQuestionsRepository= new SessionQuestionsRemoteDataSource();
    QuestionsPresenter.QuestionsJsonData[] questionsJsonData;


    public QuestionsPresenter(@NonNull SessionQuestionsRemoteDataSource sessionQuestionsRepository, @NonNull QuestionsContract.View sessionQuestionsView) {
        Log.i("question presenter","question presenter");
        mSessionQuestionsRepository = checkNotNull(sessionQuestionsRepository, "sessionQuestionsRepository cannot be null");
        mSessionQuestionView = checkNotNull(sessionQuestionsView, "sessionQuestionView cannot be null!");
        mSessionQuestionView.setPresenter(this);
    }
    @Override
    public void start() {

        loadQuestions();
    }

    public class QuestionsJsonData {

        String _question;
        String upvotes;

    }


    @Override
    public void loadQuestions() {

        ArrayList<JSONObject> answers = mSessionQuestionsRepository.getDataInJson("111");
        Log.i("answers",answers.toString());
        Gson gson = new Gson();
        questionsJsonData=gson.fromJson(answers.toString(), QuestionsPresenter.QuestionsJsonData[].class);
        processLoadedQuestions(questionsJsonData);

    }

    @Override
    public void addNewQuestion(String question) {

        mSessionQuestionsRepository.addQuestion(question,"111");
        loadQuestions();

    }

    @Override
    public void processEmptyQuestion() {

    }

    public void processLoadedQuestions(QuestionsPresenter.QuestionsJsonData[] questionsJsonData)
    {
        if(questionsJsonData.length!=0)
        {
            Log.i("question json data",questionsJsonData.toString());
            mSessionQuestionView.showAddedQuestion(questionsJsonData);
        }
    }

    @Override
    public void upvoteQuestion() {

    }



}

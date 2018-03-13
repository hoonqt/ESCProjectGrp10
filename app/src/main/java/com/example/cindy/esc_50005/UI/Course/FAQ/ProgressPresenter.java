package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.cindy.esc_50005.UI.Session.QuestionsContract;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 1002215 on 12/3/18.
 */

public class ProgressPresenter implements ProgressContract.Presenter {

    private final ProgressContract.View mCourseProgressView;
    private SessionQuestionsRemoteDataSource mSessionQuestionsRepository= new SessionQuestionsRemoteDataSource();
    ProgressPresenter.QuizJsonData[] quizJsonData;


    public ProgressPresenter(@NonNull SessionQuestionsRemoteDataSource sessionQuestionsRepository, @NonNull ProgressContract.View courseProgressView) {
        Log.i("question presenter","question presenter");
        mSessionQuestionsRepository = checkNotNull(sessionQuestionsRepository, "sessionQuestionsRepository cannot be null");
        mCourseProgressView = checkNotNull(courseProgressView, "courseProgressView cannot be null!");
        mCourseProgressView.setPresenter(this);
    }
    @Override
    public void start() {

        loadScores();
    }

    @Override
    public void loadScores() {
//        ArrayList<JSONObject> scores = mSessionQuestionsRepository.getDataInJson("111");
//        Log.i("scores",scores.toString());
        ArrayList<JSONObject> quiz = new ArrayList<JSONObject>();
        try{
            JSONObject obj = new JSONObject();
            for(int i = 0; i<5; i++){
                obj.put("name","Quiz " + i);
//                obj.put("score", i * 30);
                quiz.add(obj);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        for(int j = 0; j<quiz.size(); j++){

        }
        Gson gson = new Gson();
        quizJsonData=gson.fromJson(quiz.toString(), ProgressPresenter.QuizJsonData[].class);
        processLoadedScores(quizJsonData);

    }

    @Override
    public void processAverage() {

    }

    public class QuizJsonData {

        String quiz;
        String score;

    }


    public void processLoadedScores(ProgressPresenter.QuizJsonData[] quizJsonData)
    {
        if(quizJsonData.length!=0)
        {
            Log.i("quiz json data",quizJsonData.toString());
            mCourseProgressView.showProgress(quizJsonData);
        }
    }



}


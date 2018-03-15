package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.cindy.esc_50005.Database.ScoreDB.ScoreRetriever;
import com.example.cindy.esc_50005.UI.Session.QuestionsContract;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 1002215 on 12/3/18.
 */

public class ProgressPresenter implements ProgressContract.Presenter {

    public static final String TAG = "ProgressPresenter";

    private final ProgressContract.View mProgressView;
    private ScoreRetriever mProgressRepository;
    ArrayList<JSONObject> progressJsonData;

    public ProgressPresenter(@NonNull ProgressContract.View progressView) {
        mProgressRepository = new ScoreRetriever();
        mProgressView = checkNotNull(progressView, "progressView cannot be null!");
        mProgressView.setPresenter(this);
    }

    @Override
    public void start() {
        loadScores();
    }

    @Override
    public void loadScores() {
        progressJsonData = mProgressRepository.getScores("1002215","111");
        processScores(progressJsonData);

        Log.i(TAG, "LoadScores size is " + progressJsonData.size());
    }


    public void processScores(ArrayList<JSONObject> progressJsonData) {
        ArrayList<Double> scoreList = new ArrayList<Double>();
        Log.i(TAG, "Length of progressJsonData = " + progressJsonData.size());

        if (progressJsonData.size() != 0) {

            for(int i = 0; i<progressJsonData.size();i++){
                try{
                    scoreList.add(progressJsonData.get(i).getDouble("_score"));
                } catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        mProgressView.showProgress(scoreList);


    }



    @Override
    public void processAverage() {

    }
}

//public class ProgressPresenter implements ProgressContract.Presenter {
//
//    private final ProgressContract.View mCourseProgressView;
//    private SessionQuestionsRemoteDataSource mSessionQuestionsRepository= new SessionQuestionsRemoteDataSource();
//    ProgressPresenter.QuizJsonData[] quizJsonData;
//
//
//    public ProgressPresenter(@NonNull SessionQuestionsRemoteDataSource sessionQuestionsRepository, @NonNull ProgressContract.View courseProgressView) {
//        Log.i("question presenter","question presenter");
//        mSessionQuestionsRepository = checkNotNull(sessionQuestionsRepository, "sessionQuestionsRepository cannot be null");
//        mCourseProgressView = checkNotNull(courseProgressView, "courseProgressView cannot be null!");
//        mCourseProgressView.setPresenter(this);
//    }
//    @Override
//    public void start() {
//
//        loadScores();
//    }
//
//    @Override
//    public void loadScores() {
////        ArrayList<JSONObject> scores = mSessionQuestionsRepository.getDataInJson("111");
////        Log.i("scores",scores.toString());
//        ArrayList<JSONObject> quiz = new ArrayList<JSONObject>();
//        try{
//            JSONObject obj = new JSONObject();
//            for(int i = 0; i<5; i++){
//                obj.put("name","Quiz " + i);
////                obj.put("score", i * 30);
//                quiz.add(obj);
//            }
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        for(int j = 0; j<quiz.size(); j++){
//
//        }
//        Gson gson = new Gson();
//        quizJsonData=gson.fromJson(quiz.toString(), ProgressPresenter.QuizJsonData[].class);
//        processLoadedScores(quizJsonData);
//
//    }
//
//    @Override
//    public void processAverage() {
//
//    }
//
//    public class QuizJsonData {
//
//        String quiz;
//        String score;
//
//    }
//
//
//    public void processLoadedScores(ProgressPresenter.QuizJsonData[] quizJsonData)
//    {
//        if(quizJsonData.length!=0)
//        {
//            Log.i("quiz json data",quizJsonData.toString());
//            mCourseProgressView.showProgress(quizJsonData);
//        }
//    }
//
//
//
//}


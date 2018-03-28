package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;
import android.util.Log;


import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.cindy.esc_50005.Database.Progress.NewQuizScoresDO;
import com.example.cindy.esc_50005.Database.Progress.ProgressRemoteDataSource;


import org.json.JSONObject;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 1002215 on 12/3/18.
 */

public class ProgressPresenter implements ProgressContract.Presenter {

    public static final String TAG = "ProgressPresenter";

    private final ProgressContract.View mProgressView;
    private ProgressRemoteDataSource mProgressRepository;
    ArrayList<NewQuizScoresDO> progressArrayList;
    ArrayList<NewQuizScoresDO> nameList;

    public ProgressPresenter(@NonNull ProgressContract.View progressView) {
        mProgressRepository = new ProgressRemoteDataSource();
        mProgressView = checkNotNull(progressView, "progressView cannot be null!");
        mProgressView.setPresenter(this);
    }

    @Override
    public void start() {
        loadScores();
    }

    @Override
    public void loadScores() {
        progressArrayList = mProgressRepository.getScores("1002210","50.001");// need to change it to base on the user login details
        processScores(progressArrayList);

        Log.i(TAG, "LoadScores size is " + progressArrayList.size());
    }


    public void processScores(ArrayList<NewQuizScoresDO> progressArrayList) {
        ArrayList<Double> scoreList = new ArrayList<Double>();
        Log.i(TAG, "Length of progressArrayList = " + progressArrayList.size());

        if (progressArrayList.size() != 0) {

            for(int i = 0; i<progressArrayList.size();i++){
                try{
                    scoreList.add(progressArrayList.get(i).getScore());
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

    @Override
    public void loadNames() {
        nameList = mProgressRepository.getNames("1002210","50.001");// need to change it to base on the user login details
        processNames(nameList);

        Log.i(TAG, "LoadName size is " + nameList.size());
    }

    public void processNames(ArrayList<NewQuizScoresDO> nameList) {
        ArrayList<String> names = new ArrayList<String>();
        Log.i(TAG, "Length of nameList = " + nameList.size());

        if (nameList.size() != 0) {

            for(int i = 0; i<nameList.size();i++){
                try{
                    names.add(nameList.get(i).getName());
                } catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        mProgressView.showNames(names);


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


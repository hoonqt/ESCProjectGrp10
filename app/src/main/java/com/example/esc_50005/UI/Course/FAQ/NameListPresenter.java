package com.example.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.esc_50005.Database.Progress.QuizScores4DO;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 1002215 on 27/3/18.
 */

public class NameListPresenter implements ProgressContract.Presenter {

    public static final String TAG = "NameList Presenter";

    private final ProgressContract.View mNameListView;
    private ProgressRemoteDataSource mProgressRepository;
    ArrayList<QuizScores4DO> progressArrayList;
    ArrayList<QuizScores4DO> nameList;
    ArrayList<String> studIdArrayList;
    ArrayList<Double> avgList;
    private String courseId;

    public NameListPresenter(@NonNull ProgressContract.View nameListView) {
        mProgressRepository = new ProgressRemoteDataSource();
        mNameListView = checkNotNull(nameListView, "progressView cannot be null!");
        mNameListView.setPresenter(this);
    }

    @Override
    public void start() {
//        loadScores();
        loadNames();
//        processAverage(progressArrayList);
    }

    @Override
    public void setStudentId(String studentId) {

    }

    @Override
    public void setCourseId(String courseId) {
        this.courseId=courseId;
    }

    @Override
    public void loadScores() {
        progressArrayList = mProgressRepository.getScores(courseId,"1002212");// need to change it to base on the user login details
//        processScores(progressArrayList);

        Log.i(TAG, "LoadScores size is " + progressArrayList.size());
    }




    public void processScores(ArrayList<QuizScores4DO> progressArrayList) {
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

        mNameListView.showProgress(scoreList);


    }



    @Override
    public ArrayList<Double> processAverage(ArrayList<QuizScores4DO> progressArrayList) {
        ArrayList<Double> scoreList = new ArrayList<Double>();

        Double avg = 0.0;

        String student;
        if (studIdArrayList.size()!=0){
            Log.i(TAG, "Process AVG studIdArrayList size: " + studIdArrayList.size());
            for(int i=0; i<studIdArrayList.size();i++){
                ArrayList<QuizScores4DO> temp = new ArrayList<>();
                Log.i(TAG, "Process AVG studId: " + studIdArrayList.get(i).substring(0,7));
                temp = mProgressRepository.getScores(courseId,studIdArrayList.get(i).substring(0,7));
//                Log.i(TAG, "Name: " + temp.get(i).getName());
                Log.i(TAG, "Process AVG temp size:  " + temp.size());
                Double total=0.0;
                for(int j=0;j<temp.size();j++){

                    total+=temp.get(j).getScore();
                    Log.i(TAG, "Process AVG total: " + total);
                }
                avg = Math.round(total/temp.size() * 100.0) / 100.0;

                if(avgList==null){
                    scoreList.add(avg);
                    avgList=scoreList;
                } else{
                    avgList.add(avg);
                }
            }

        }
        return avgList;

    }

    @Override
    public void loadNames() {
        Log.i(TAG, "Course Id" + courseId);

        nameList = mProgressRepository.getNames(courseId);// need to change it to base on the user login details

        if (nameList.size()==0){
            mNameListView.showNoName();
            Log.i(TAG, "HERE1" + courseId);
        } else{
            processNames(nameList);
            Log.i(TAG, "LoadName size is " + nameList.size() + nameList.get(0).getName());
//        for(int i=0; i<nameList.size();i++){
//            Log.i(TAG, "LoadName size is " + nameList.size() + nameList.get(i).getName());
//        }
        }


    }

    public void processNames(ArrayList<QuizScores4DO> nameList) {
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> studentIds = new ArrayList<String>();
        Log.i(TAG, "Length of nameList = " + nameList.size());


        if (nameList.size() != 0) {

            for(int i = 0; i<nameList.size();i++){
                if(names.size()==0){
                    try{
                        names.add(nameList.get(i).getName());
                        studentIds.add(nameList.get(i).getStudentIDSessionID());
                        Log.i(TAG, "firstname = " + nameList.get(i).getName());
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                } else{
                    boolean exist = false;
                    for(int j = 0; j<names.size();j++){
                        if(names.get(j).equals(nameList.get(i).getName())){
                            exist = true;
                        }
                    }
                    if(!exist){
                        try{
                            names.add(nameList.get(i).getName());
                            studentIds.add(nameList.get(i).getStudentIDSessionID());
                            Log.i(TAG, "non-existent name = " + nameList.get(i).getName());
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }



            }
        }
        studIdArrayList=studentIds;

        mNameListView.showNames(names,studentIds, processAverage(progressArrayList));


    }
}

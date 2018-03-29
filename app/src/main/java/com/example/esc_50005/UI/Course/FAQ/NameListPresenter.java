package com.example.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.Progress.NewQuizScoresDO;
import com.example.esc_50005.Database.Progress.ProgressRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 1002215 on 27/3/18.
 */

public class NameListPresenter implements ProgressContract.Presenter {

    public static final String TAG = "ProgressPresenter";

    private final ProgressContract.View mNameListView;
    private ProgressRemoteDataSource mProgressRepository;
    ArrayList<NewQuizScoresDO> progressArrayList;
    ArrayList<NewQuizScoresDO> nameList;

    public NameListPresenter(@NonNull ProgressContract.View nameListView) {
        mProgressRepository = new ProgressRemoteDataSource();
        mNameListView = checkNotNull(nameListView, "progressView cannot be null!");
        mNameListView.setPresenter(this);
    }

    @Override
    public void start() {
        loadScores();
        loadNames();
        processAverage(progressArrayList);
//        processScores(progressArrayList);
    }

    @Override
    public void loadScores() {
        progressArrayList = mProgressRepository.getScores("1002212","50.004");// need to change it to base on the user login details
//        processScores(progressArrayList);

        Log.i(TAG, "LoadScores size is " + progressArrayList.size() + progressArrayList.get(0).getScore());
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

        mNameListView.showProgress(scoreList);


    }



    @Override
    public double processAverage(ArrayList<NewQuizScoresDO> progressArrayList) {
        ArrayList<Double> scoreList = new ArrayList<Double>();
        double total=0;
        double avg = 0;
        String student;


        if (progressArrayList.size() != 0) {
            Log.i(TAG, "Length of progressArrayList = " + progressArrayList.size());
            student = progressArrayList.get(0).getStudentIDsubjectID();//might need to change in the future
            for(int i = 0; i<progressArrayList.size();i++){
                try{
                    total += progressArrayList.get(i).getScore();
                } catch(Exception e){
                    e.printStackTrace();
                }

            }
            avg = total/progressArrayList.size();
        }

        return avg;

    }

    @Override
    public void loadNames() {
        nameList = mProgressRepository.getNames("1002212","50.004");// need to change it to base on the user login details
        processNames(nameList);

        Log.i(TAG, "LoadName size is " + nameList.size() + nameList.get(0).getName());
    }

    public void processNames(ArrayList<NewQuizScoresDO> nameList) {
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> studentIds = new ArrayList<String>();
        Log.i(TAG, "Length of nameList = " + nameList.size());


        if (nameList.size() != 0) {

            for(int i = 0; i<nameList.size();i++){
                if(names.size()==0){
                    try{
                        names.add(nameList.get(i).getName());
                        studentIds.add(nameList.get(i).getStudentIDsubjectID());
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
                            Log.i(TAG, "non-existent name = " + nameList.get(i).getName());
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }



            }
        }

        mNameListView.showNames(names,studentIds, processAverage(progressArrayList));


    }
}

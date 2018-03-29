package com.example.esc_50005.Database.Progress;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProgressRemoteDataSource implements ProgressDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<JSONObject> datainjson;
    ArrayList<NewQuizScoresDO> progressArrayList;
    ArrayList<NewQuizScoresDO> nameList;

    public ProgressRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();


    }

    public void putScores(String userid, String subjectcode, String quizname, Double score, String name) {

        final NewQuizScoresDO quizscore = new NewQuizScoresDO();
        quizscore.setStudentIDsubjectID(userid+subjectcode);
        quizscore.setQuizID(quizname);
        quizscore.setScore(score);
        quizscore.setName(name);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(quizscore);
                // Item saved
            }
        }).start();


    }


    public ArrayList<NewQuizScoresDO> getScores(final String userId, final String subjectCode) {

        progressArrayList = new ArrayList<NewQuizScoresDO>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                NewQuizScoresDO scores = new NewQuizScoresDO();
                scores.setStudentIDsubjectID(userId+subjectCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(scores);

                PaginatedList<NewQuizScoresDO> result = dynamoDBMapper.query(NewQuizScoresDO.class, queryExpression);

                for (NewQuizScoresDO score : result) {
                    progressArrayList.add(score);
                    Log.i("scores in prds","scores: " + score.getScore().toString());
                }

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i("Progresslist in prds", "progressList" + progressArrayList.toString());

        return progressArrayList;

    }

    public ArrayList<NewQuizScoresDO> getNames(final String userId, final String subjectCode) {

//        dataInJson = new ArrayList<>();

        nameList = new ArrayList<NewQuizScoresDO>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                NewQuizScoresDO names = new NewQuizScoresDO();
                names.setStudentIDsubjectID(userId+subjectCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(names);

                PaginatedList<NewQuizScoresDO> result = dynamoDBMapper.query(NewQuizScoresDO.class, queryExpression);

                for (NewQuizScoresDO name : result) {
                    nameList.add(name);
//                    Log.i("scores in prds","scores: " + name.getName().toString());
                }

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i("NameList in prds", "progressList" + nameList.toString());

        return nameList;

    }

//    public ArrayList<JSONObject> getScores(final String userid,final String subjectcode) {
//
//        getFromDatabase(userid, subjectcode);
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        }
//
//        catch (InterruptedException ex) {
//
//        }
//
//        return datainjson;
//    }


}

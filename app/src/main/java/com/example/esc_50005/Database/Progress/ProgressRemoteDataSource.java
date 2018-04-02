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
    ArrayList<QuizScores2DO> progressArrayList;
    ArrayList<QuizScores2DO> nameList;

    public ProgressRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();


    }

    public void putScores(String userid, String subjectcode,String sessionID,  String quizname, Double score, String name) {

        final QuizScores2DO quizscore = new QuizScores2DO();
        quizscore.setSubjectCodeSessionID(subjectcode+sessionID);
        quizscore.setStudentID(userid);
        quizscore.setQuizName(quizname);
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


    public ArrayList<QuizScores2DO> getScores(final String subjectCode, final String sessionID) {

        progressArrayList = new ArrayList<QuizScores2DO>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                QuizScores2DO scores = new QuizScores2DO();
                scores.setSubjectCodeSessionID(subjectCode+sessionID);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(scores);

                PaginatedList<QuizScores2DO> result = dynamoDBMapper.query(QuizScores2DO.class, queryExpression);

                for (QuizScores2DO score : result) {
                    progressArrayList.add(score);
                    Log.i("scores in prds","scores: " + score.getScore().toString());
                }

            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i("Progresslist in prds", "progressList" + progressArrayList.toString());

        return progressArrayList;

    }

public ArrayList<QuizScores2DO> getNames(final String subjectCode, final String sessionID) {

//        dataInJson = new ArrayList<>();

        nameList = new ArrayList<QuizScores2DO>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                QuizScores2DO names = new QuizScores2DO();

                names.setSubjectCodeSessionID(subjectCode+sessionID);


                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(names);

                PaginatedList<QuizScores2DO> result = dynamoDBMapper.query(QuizScores2DO.class, queryExpression);
                Log.i("size",Integer.toString(result.size()));
                for (QuizScores2DO name : result) {
                    nameList.add(name);
//                    Log.i("scores in prds","scores: " + name.getName().toString());
                }

            }
        });

        retriever.start();

        try {
            retriever.join();
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

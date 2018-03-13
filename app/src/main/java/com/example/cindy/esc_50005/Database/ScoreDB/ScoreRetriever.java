package com.example.cindy.esc_50005.Database.ScoreDB;


import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScoreRetriever {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<JSONObject> datainjson;

    public ScoreRetriever() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();


    }

    public void putScores(String userid, String subjectcode, String quizname, Double score) {

        final NewQuizScoresDO quizscore = new NewQuizScoresDO();
        quizscore.setStudentIDsubjectID(userid+subjectcode);
        quizscore.setQuizID(quizname);
        quizscore.setScore(score);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(quizscore);
                // Item saved
            }
        }).start();


    }

    public void getFromDatabase(final String userid,final String subjectcode) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                NewQuizScoresDO scores = new NewQuizScoresDO();
                scores.setStudentIDsubjectID(userid+subjectcode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(scores);

                long startTime = System.currentTimeMillis();

                PaginatedList<NewQuizScoresDO> result = dynamoDBMapper.query(NewQuizScoresDO.class,queryExpression);

                Gson gson = new Gson();

                StringBuilder stringBuilder = new StringBuilder();

                datainjson = new ArrayList<>();

                for (int i = 0;i<result.size();i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));

                    try {
                        datainjson.add(new JSONObject(jsonFormOfItem));
                    }

                    catch (JSONException ex) {
                        System.out.println(ex);
                    }

                }


            }
        }).start();

    }

    public ArrayList<JSONObject> getScores(final String userid,final String subjectcode) {

        getFromDatabase(userid, subjectcode);

        try {
            TimeUnit.SECONDS.sleep(2);
        }

        catch (InterruptedException ex) {

        }

        return datainjson;
    }


}

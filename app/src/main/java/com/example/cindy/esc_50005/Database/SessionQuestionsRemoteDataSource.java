package com.example.cindy.esc_50005.Database;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by hoonqt on 1/3/18.
 */

public class SessionQuestionsRemoteDataSource implements SessionQuestionsDataSource {

    DynamoDBMapper dynamoDBMapper;
//    private String finalresult;
    private StringBuilder finalResult=new StringBuilder();

    private void setFinalResult(String append)
    {
        this.finalResult.append(append);
    }
    private StringBuilder getFinalResult()
    {
        return this.finalResult;
    }

    public SessionQuestionsRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

    }


    private String finalresult;
    private ArrayList<JSONObject> datainjson;

    @Override
    public void addQuestion(String question, String sessionCode) {

        final SessionQuestionsDO newQuestion = new SessionQuestionsDO();

        newQuestion.setSessioncode(sessionCode);
        newQuestion.setAnswers(new ArrayList<String>());
        newQuestion.setQuestion(question);
        newQuestion.setUpvote(0.0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();


    }

    @Override
    public void removeQuestion(String question, String sessionCode) {

        final SessionQuestionsDO deleteQn = new SessionQuestionsDO();
        deleteQn.setSessioncode(sessionCode);
        deleteQn.setQuestion(question);

        new Thread(new Runnable() {
            @Override
            public void run() {

                dynamoDBMapper.delete(deleteQn);

            }
        }).start();

    }

    @Override
    public void updateQuestion(String oldquestion, String sessionCode, String newQuestion) {

    }

    @Override
    public void getQuestionsList(final String sessionCode) {

        datainjson = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                SessionQuestionsDO faq = new SessionQuestionsDO();
                faq.setSessioncode(sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(faq);

                PaginatedList<SessionQuestionsDO> result = dynamoDBMapper.query(SessionQuestionsDO.class,queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0;i<result.size();i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");

                    try {
                        datainjson.add(new JSONObject(jsonFormOfItem));
                    }

                    catch (JSONException ex) {
                        System.out.println(ex);
                    }


                }
                Log.i("resultSize",Integer.toString(result.size()));
                setFinalResult(stringBuilder.toString());
                Log.i("inside final result",getFinalResult().toString());


            }
        }).start();



    }

    public void JSONprocessor(ArrayList<JSONObject> tobeprocessed) {

        ArrayList<Question> allquestions = new ArrayList<>();

        for (int i = 0;i<tobeprocessed.size();i++) {

            JSONObject object = tobeprocessed.get(i);

            try {
                String question = object.getString("_question");
            }

            catch (JSONException ex) {

            }

        }


    }

    @Override
    public void findQuestionsById() {

    }

    public ArrayList<JSONObject> getDatainjson(String sessionCode) {

        getQuestionsList(sessionCode);

        try {
            TimeUnit.MILLISECONDS.sleep(50);
        }

        catch (InterruptedException ex) {

        }

        return datainjson;
    }
}

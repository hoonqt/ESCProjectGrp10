package com.example.cindy.esc_50005.Database;

import android.util.Log;

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

public class SessionQuestionsRemoteDataSource implements SessionQuestionsDataSource {

    DynamoDBMapper dynamoDBMapper;
    private StringBuilder finalResult=new StringBuilder();

    private void setFinalResult(String resultsFromQuery)
    {
        this.finalResult.append(resultsFromQuery);
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

    private ArrayList<JSONObject> dataInJson;

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

        dataInJson = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                SessionQuestionsDO sessionSelected = new SessionQuestionsDO();
                sessionSelected.setSessioncode(sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(sessionSelected);

                PaginatedList<SessionQuestionsDO> result = dynamoDBMapper.query(SessionQuestionsDO.class,queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0;i<result.size();i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");

                    try {
                        dataInJson.add(new JSONObject(jsonFormOfItem));
                    }

                    catch (JSONException ex) {
                        System.out.println(ex);
                    }

                }

                Log.i("stringBuilder",stringBuilder.toString());

                setFinalResult(stringBuilder.toString());

            }
        }).start();

    }

    public void JSONprocessor(ArrayList<JSONObject> tobeprocessed) {

        ArrayList<Question> allQuestions = new ArrayList<>();

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

    public ArrayList<JSONObject> getDataInJson(String sessionCode) {

        getQuestionsList(sessionCode);

        try {
            TimeUnit.SECONDS.sleep(2);
        }

        catch (InterruptedException ex) {

        }
        Log.i("dataInJson",dataInJson.toString());
        return dataInJson;
    }
}
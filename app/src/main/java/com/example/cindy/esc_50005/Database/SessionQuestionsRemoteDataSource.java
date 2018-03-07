package com.example.cindy.esc_50005.Database;

<<<<<<< HEAD
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
=======
>>>>>>> Database
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hoonqt on 1/3/18.
 */

public class SessionQuestionsRemoteDataSource implements SessionQuestionsDataSource {

    DynamoDBMapper dynamoDBMapper;
<<<<<<< HEAD
//    private String finalresult;
//    JSONObject datainjson=new JSONObject();
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
=======
    private String finalresult;
    JSONArray datainjson;
>>>>>>> Database

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

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<JSONObject> allthedata = new ArrayList<>();

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
<<<<<<< HEAD

                }
                Log.i("resultSize",Integer.toString(result.size()));
                setFinalResult(stringBuilder.toString());
                Log.i("inside final result",getFinalResult().toString());

                try {
                    JSONObject datainjson = new JSONObject(stringBuilder.toString());
                    Log.i("inside data",datainjson.toString());
                }
=======

                    try {
                        allthedata.add(new JSONObject(jsonFormOfItem));
                    }
>>>>>>> Database

                    catch (JSONException e) {
                        System.out.println(e);
                    }
                }

                JSONprocessor(allthedata);


            }
        }).start();

<<<<<<< HEAD
        try{
            Log.i("outside final result", getFinalResult().toString());
            JSONObject data=new JSONObject(getFinalResult().toString());
            Log.i("outside data",data.toString());
            return data;
        }
        catch(Exception ex)
        {

        }

        return null;
=======
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


>>>>>>> Database
    }

    @Override
    public void findQuestionsById() {

    }
}

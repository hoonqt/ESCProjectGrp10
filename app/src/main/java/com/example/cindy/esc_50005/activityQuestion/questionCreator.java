package com.example.cindy.esc_50005.activityQuestion;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.cindy.esc_50005.Database.SessionQuestionsDO;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoonqt on 5/3/18.
 */

public class questionCreator {

    DynamoDBMapper dynamoDBMapper;
    JSONObject datainjson;

    public void createQuestion(String courseID, String sessionID,String question, List<String> options,Double answer) {

        final ActivityQuestionsDO newQuestion = new ActivityQuestionsDO();
        newQuestion.setCourseID(courseID);
        newQuestion.setSessionNO(sessionID);
        newQuestion.setQuestion(question);
        newQuestion.setOptions(options);
        newQuestion.setCorrectOption(answer);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();

    }

    public JSONObject getQuestions(final String courseID, final String sessionID) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SessionQuestionsDO faq = new SessionQuestionsDO();
                faq.setSessioncode(courseID);
                faq.setSessioncode(sessionID);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(faq);

                PaginatedList<SessionQuestionsDO> result = dynamoDBMapper.query(SessionQuestionsDO.class,queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0;i<result.size();i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");
                }

                try {
                    datainjson = new JSONObject(stringBuilder.toString());
                }

                catch (JSONException ex) {
                    System.out.println(ex);
                }

            }
        }).start();

        return datainjson;

    }

    public static void main(String[] args) {

        questionCreator creator = new questionCreator();

        List<String> options = new ArrayList<>();
        options.add("I don't know");
        options.add("I know");


        creator.createQuestion("abc123","123","What is antimatter?",options,0.0);

    }




}

package com.example.cindy.esc_50005.Database.activityQuestion;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hoonqt on 5/3/18.
 */

public class questionCreator {

    DynamoDBMapper dynamoDBMapper;
    JSONObject datainjson;

    public questionCreator() {

    }

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


                try {
                    datainjson = new JSONObject(gson.toJson(result.get(0)));
                    TimeUnit.SECONDS.sleep(2);

                }

                catch (JSONException ex) {
                    System.out.println(ex);
                }

                catch (InterruptedException ex) {
                    System.out.println(ex);
                }



            }
        }).start();

        return datainjson;

    }

}

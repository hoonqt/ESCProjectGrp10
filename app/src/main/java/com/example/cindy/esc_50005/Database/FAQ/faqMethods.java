package com.example.cindy.esc_50005.Database.FAQ;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hoonqt on 2/3/18.
 */

public class faqMethods {

    DynamoDBMapper dynamoDBMapper;
    JSONObject datainjson;

    public void addFaq(String question, String courseID, String date, String answer, String userID) {

        final FaqQuestionDO newFaq = new FaqQuestionDO();
        newFaq.setQuestion(question);
        newFaq.setAnswer(answer);
        newFaq.setDate(date);
        newFaq.setCourseID(courseID);
        newFaq.setUserId(userID);




        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newFaq);
                // Item saved
            }
        }).start();

    }

    public void upvoteQn(String question, String courseID) {
        final FaqQuestionDO newQuestion = new FaqQuestionDO();

        newQuestion.setQuestion(question);
        newQuestion.setCourseID(courseID);
        newQuestion.setUpVotes(newQuestion.getUpVotes()+1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();
    }

    //Process to get all the entries in the database for  certain session code

    public void queryBase(final String sessionCode) {
        String result;
        new Thread(new Runnable() {
            @Override
            public void run() {
                FaqQuestionDO faq = new FaqQuestionDO();
                faq.setCourseID(sessionCode);

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

    }

}

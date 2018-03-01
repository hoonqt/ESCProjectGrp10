package com.example.cindy.esc_50005.Database;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by hoonqt on 1/3/18.
 */

public class SessionQuestionsRemoteDataSource implements SessionQuestionsDataSource {

    DynamoDBMapper dynamoDBMapper;
    private String finalresult;

    @Override
    public void addQuestion(String question, String sessionCode) {

        final NewfaqDO newQuestion = new NewfaqDO();

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

        final NewfaqDO deleteQn = new NewfaqDO();
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
    public String getQuestionsList(final String sessionCode) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                NewfaqDO faq = new NewfaqDO();
                faq.setSessioncode(sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(faq);

                PaginatedList<NewfaqDO> result = dynamoDBMapper.query(NewfaqDO.class,queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0;i<result.size();i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");
                }

                finalresult = stringBuilder.toString();

            }
        }).start();

        return finalresult;
    }

    @Override
    public void findQuestionsById() {

    }
}

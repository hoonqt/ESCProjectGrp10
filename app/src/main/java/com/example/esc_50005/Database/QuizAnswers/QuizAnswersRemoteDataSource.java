package com.example.esc_50005.Database.QuizAnswers;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;

import java.util.ArrayList;

/**
 * Created by hoonqt on 10/4/18.
 */

public class QuizAnswersRemoteDataSource implements QuizAnswersDataSource{

    DynamoDBMapper dynamoDBMapper;
    ArrayList<QuizAnswersDO> answerArrayList;

    public QuizAnswersRemoteDataSource() {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void addQuestion(String subjectCodesessionCode, String quizNameStudentID, String answer, String time) {

        final QuizAnswersDO newAnswer = new QuizAnswersDO();
        newAnswer.setSubjectCodeSessionCode(subjectCodesessionCode);
        newAnswer.setQuizNameStudentID(quizNameStudentID);
        newAnswer.setAnswer(answer);
        newAnswer.setTime(time);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newAnswer);
            }
        }).start();

    }

    @Override
    public ArrayList<QuizAnswersDO> getQuestions(final String subjectCodesessionCode) {

        answerArrayList = new ArrayList<>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {
                QuizAnswersDO selected = new QuizAnswersDO();
                selected.setSubjectCodeSessionCode(subjectCodesessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(selected);

                PaginatedList<QuizAnswersDO> result = dynamoDBMapper.query(QuizAnswersDO.class,queryExpression);

                for (QuizAnswersDO answer: result) {
                    answerArrayList.add(answer);
                }
            }
        });

        retriever.start();

        try {
            retriever.join();
        }

        catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return answerArrayList;
    }
}

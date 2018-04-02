package com.example.esc_50005.Database.Progress;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

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

    public void putScores(String studentID, String subjectcode,String sessionID,  String quizname, Double score, String name) {

        final QuizScores3DO quizscore = new QuizScores3DO();

        quizscore.setStudentID(studentID);
        quizscore.setName(name);
        quizscore.setSubjectCode(subjectcode);
        quizscore.setQuizName(quizname);
        quizscore.setScore(score);
        quizscore.setSessionID(sessionID);



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

                names.setSubjectCodeSessionID(subjectCode);

                Condition rangeKeyCondition = new Condition()
                        .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                        .withAttributeValueList(new AttributeValue().withS(subjectCode));


                DynamoDBQueryExpression queryExpression =
                        new DynamoDBQueryExpression()
                                .withRangeKeyCondition("SubjectCodeSessionID",rangeKeyCondition);

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

    public ArrayList<QuizScores2DO> getAllStudent(final String studentID) {

        progressArrayList = new ArrayList<QuizScores2DO>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                QuizScores2DO scores = new QuizScores2DO();
                scores.setStudentID(studentID);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(scores).withConsistentRead(false);

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

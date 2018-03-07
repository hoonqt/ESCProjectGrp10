package com.example.trialesc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DynamoDBMapper dynamoDBMapper;
    String newQn;
    EditText questionBox;
    TextView textbox;

    String longtext = "";
    JSONObject datainjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AWSMobileClient.getInstance().initialize(this).execute();

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();


    }

    public void onClickaddDB(View view) {
        questionBox = (EditText)findViewById(R.id.questionBox);
        newQn = questionBox.getText().toString();
        postQuestion(newQn,"abc1234");
    }

    public void postQuestion(String question, String sessionCode) {

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

    public void upvoteQn(String question, String sessionCode) {
        final SessionQuestionsDO newQuestion = new SessionQuestionsDO();

        newQuestion.setSessioncode(sessionCode);
        newQuestion.setQuestion(question);
        newQuestion.setUpvote(newQuestion.getUpvote()+1.0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();
    }

    //Answers are all stored in an ArrayList, we can add more complications later

    public void answerQn(String question, String sessionCode, String answer) {
        final SessionQuestionsDO newQuestion = new SessionQuestionsDO();

        newQuestion.setSessioncode(sessionCode);
        ArrayList<String> currentanswers = new ArrayList<>();
        newQuestion.setAnswers(currentanswers);
        currentanswers.add(answer);
        newQuestion.setQuestion(question);

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
                }

                longtext = stringBuilder.toString();

                try {
                    datainjson = new JSONObject(longtext);
                }

                catch (JSONException ex) {
                    System.out.println(ex);
                }

            }
        }).start();


    }

    public void onClickshowDB(View view) {

        queryBase("abc1234");
        textbox = (TextView)findViewById(R.id.showmaker);
        textbox.setText(longtext);

    }



}

package com.example.cindy.esc_50005.Database.UsersInformation;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class UsersInformationRemoteDataSource implements UsersInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<UsersInformationDO> usersArrayList;
    public static final String TAG = "UsersInformationRemote";

    public UsersInformationRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void addUser(final UsersInformation userInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(userInformation);
            }
        }).start();

    }

    @Override
    public void removeUser(final UsersInformation userInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(userInformation);
            }
        }).start();

    }

    public ArrayList<UsersInformationDO> queryUser(final String username, String password, final String userType) {

        Log.i("username",userType);
        usersArrayList = new ArrayList<UsersInformationDO>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                UsersInformationDO userSelected = new UsersInformationDO();
                userSelected.setUserType(userType);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<UsersInformationDO> result = dynamoDBMapper.query(UsersInformationDO.class, queryExpression);

                for (UsersInformationDO userInformation : result) {
                    usersArrayList.add(userInformation);

                    //You gonna have to change the way you retrive stuff here.
                }


            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return usersArrayList;
    }


}
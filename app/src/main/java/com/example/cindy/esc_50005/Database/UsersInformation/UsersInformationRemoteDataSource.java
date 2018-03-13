package com.example.cindy.esc_50005.Database.UsersInformation;


import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class UsersInformationRemoteDataSource implements UsersInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<UsersInformation> usersArrayList;
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

    public ArrayList<UsersInformation> queryUser(final String username, String password, String userType) {

        usersArrayList = new ArrayList<UsersInformation>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                UsersInformationDO userSelected = new UsersInformationDO();
                userSelected.setUserType("student");
                userSelected.setUsername(username);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<UsersInformationDO> result = dynamoDBMapper.query(UsersInformationDO.class, queryExpression);

                for (UsersInformationDO userInformation : result) {

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
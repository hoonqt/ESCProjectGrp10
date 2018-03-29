package com.example.esc_50005.Database.UsersInformation;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;

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
    public void removeUser(final UsersInformationDO usersInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(usersInformation);
            }
        }).start();


    }

    @Override
    public void addUser(final UsersInformationDO userInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(userInformation);
            }
        }).start();

    }


    public void editUser(String course) {

    }
    public ArrayList<UsersInformationDO> queryParticularUser(final String username, final String password, final String userType) {
        Log.i("username",userType);
        usersArrayList = new ArrayList<UsersInformationDO>();

        Thread random = new Thread(new Runnable() {
            @Override
            public void run() {

                UsersInformationDO userSelected = new UsersInformationDO();
                Log.i("userType at db",userType);
                Log.i("username at db",username);
                userSelected.setUserType(userType);
                userSelected.setUsername(username);
                userSelected.setPassword(password);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<UsersInformationDO> result = dynamoDBMapper.query(UsersInformationDO.class, queryExpression);
                for (UsersInformationDO userInformation : result) {
                    if(userInformation.getUsername().equals(username))
                    {
                        usersArrayList.add(userInformation);
                    }
                    Log.i("gettingData","gettingData");
                    //You gonna have to change the way you retrieve stuff here.
                }



            }
        });

        random.start();

        try {
            random.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i("size for particular ",Integer.toString(usersArrayList.size()));

        return usersArrayList;

    }


    public ArrayList<UsersInformationDO> queryUser(final String username, final String password, final String userType) {

        Log.i("username",userType);
        usersArrayList = new ArrayList<UsersInformationDO>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                UsersInformationDO userSelected = new UsersInformationDO();
                Log.i("userType at db",userType);
                Log.i("username at db",username);
                userSelected.setUserType(userType);
                userSelected.setUsername(username);
                userSelected.setPassword(password);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<UsersInformationDO> result = dynamoDBMapper.query(UsersInformationDO.class, queryExpression);
                for (UsersInformationDO userInformation : result) {
                    usersArrayList.add(userInformation);
                    Log.i("gettingData","gettingData");
                    //You gonna have to change the way you retrieve stuff here.
                }



            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i("size",Integer.toString(usersArrayList.size()));

        return usersArrayList;
    }


}
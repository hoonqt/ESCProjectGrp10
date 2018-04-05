package com.example.esc_50005.Database.CoursesInformation;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class CoursesInformationRemoteDataSource implements CoursesInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<CoursesInformationDO> coursesArrayList;
    public static final String TAG = "CoursesInformationRemote";

    private static CoursesInformationRemoteDataSource INSTANCE;

//    private ArrayList<JSONObject> dataInJson;

    public static CoursesInformationRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CoursesInformationRemoteDataSource();
        }
        return INSTANCE;
    }


    public CoursesInformationRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void addCourse(final String courseId, final String courseName, final List<String> studentIds) {
        final CoursesInformationDO newCourse=new CoursesInformationDO();
        newCourse.setCourseId(courseId);
        newCourse.setCourseName(courseName);
        if(studentIds!=null)
        {
            newCourse.setCourseStudentList(studentIds);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newCourse);
            }
        }).start();

    }

    @Override
    public void removeCourse(final CoursesInformationDO userInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(userInformation);
            }
        }).start();

    }

    public ArrayList<CoursesInformationDO> queryCourses(final String courseId) {


        coursesArrayList = new ArrayList<CoursesInformationDO>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                CoursesInformationDO courseSelected = new CoursesInformationDO();

                courseSelected.setCourseId(courseId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(courseSelected);

                PaginatedList<CoursesInformationDO> result = dynamoDBMapper.query(CoursesInformationDO.class, queryExpression);

                for (CoursesInformationDO courseInformation : result) {
                    coursesArrayList.add(courseInformation);

                    //You gonna have to change the way you retrive stuff here.
                }


            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        Log.i("size of query",Integer.toString(coursesArrayList.size()));
        return coursesArrayList;
    }


}
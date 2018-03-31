package com.example.esc_50005.Database.CoursesInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-CoursesInformation")

public class CoursesInformationDO {
    private Double _courseID;
    private String _courseName;
    private List<String> _listOfStudents;
    private List<String> _sessionId;

    @DynamoDBHashKey(attributeName = "courseID")
    @DynamoDBAttribute(attributeName = "courseID")
    public Double getCourseID() {
        return _courseID;
    }

    public void setCourseID(final Double _courseID) {
        this._courseID = _courseID;
    }
    @DynamoDBRangeKey(attributeName = "courseName")
    @DynamoDBAttribute(attributeName = "courseName")
    public String getCourseName() {
        return _courseName;
    }

    public void setCourseName(final String _courseName) {
        this._courseName = _courseName;
    }
    @DynamoDBAttribute(attributeName = "listOfStudents")
    public List<String> getListOfStudents() {
        return _listOfStudents;
    }

    public void setListOfStudents(final List<String> _listOfStudents) {
        this._listOfStudents = _listOfStudents;
    }
    @DynamoDBAttribute(attributeName = "sessionId")
    public List<String> getSessionId() {
        return _sessionId;
    }

    public void setSessionId(final List<String> _sessionId) {
        this._sessionId = _sessionId;
    }

}
package com.example.cindy.esc_50005.Database.sessionsInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-SessionsInformation")

public class SessionsInformationDO {
    private String _sessionID;
    private String _courseID;
    private String _courseName;
    private List<String> _students;

    @DynamoDBHashKey(attributeName = "sessionID")
    @DynamoDBAttribute(attributeName = "sessionID")
    public String getSessionID() {
        return _sessionID;
    }

    public void setSessionID(final String _sessionID) {
        this._sessionID = _sessionID;
    }
    @DynamoDBRangeKey(attributeName = "courseID")
    @DynamoDBAttribute(attributeName = "courseID")
    public String getCourseID() {
        return _courseID;
    }

    public void setCourseID(final String _courseID) {
        this._courseID = _courseID;
    }
    @DynamoDBAttribute(attributeName = "courseName")
    public String getCourseName() {
        return _courseName;
    }

    public void setCourseName(final String _courseName) {
        this._courseName = _courseName;
    }
    @DynamoDBAttribute(attributeName = "students")
    public List<String> getStudents() {
        return _students;
    }

    public void setStudents(final List<String> _students) {
        this._students = _students;
    }

}

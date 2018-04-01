package com.example.esc_50005.Database.sessionsInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-SessionsInformation")

public class SessionsInformationDO {
    private String _sessionID;
    private String _courseID;
    private String _courseName;
    private String _dateOfCreation;
    private List<String> _listOfStudents;
    private String _sessionName;

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
    @DynamoDBAttribute(attributeName = "dateOfCreation")
    public String getDateOfCreation() {
        return _dateOfCreation;
    }

    public void setDateOfCreation(final String _dateOfCreation) {
        this._dateOfCreation = _dateOfCreation;
    }
    @DynamoDBAttribute(attributeName = "listOfStudents")
    public List<String> getListOfStudents() {
        return _listOfStudents;
    }

    public void setListOfStudents(final List<String> _listOfStudents) {
        this._listOfStudents = _listOfStudents;
    }
    @DynamoDBAttribute(attributeName = "sessionName")
    public String getSessionName() {
        return _sessionName;
    }

    public void setSessionName(final String _sessionName) {
        this._sessionName = _sessionName;
    }

}

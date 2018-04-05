package com.example.esc_50005.Database.sessionsInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;


@DynamoDBTable(tableName = "escproject-mobilehub-27166461-SessionsInformation")

public class SessionsInformationDO {
    private String _sessionId;
    private String _courseId;
    private String _sessionDate;
    private String _sessionName;
    private List<String> _sessionStudentList;

    @DynamoDBHashKey(attributeName = "sessionId")
    @DynamoDBAttribute(attributeName = "sessionId")
    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(final String _sessionId) {
        this._sessionId = _sessionId;
    }
    @DynamoDBRangeKey(attributeName = "courseId")
    @DynamoDBAttribute(attributeName = "courseId")
    public String getCourseId() {
        return _courseId;
    }

    public void setCourseId(final String _courseId) {
        this._courseId = _courseId;
    }
    @DynamoDBAttribute(attributeName = "sessionDate")
    public String getSessionDate() {
        return _sessionDate;
    }

    public void setSessionDate(final String _sessionDate) {
        this._sessionDate = _sessionDate;
    }
    @DynamoDBAttribute(attributeName = "sessionName")
    public String getSessionName() {
        return _sessionName;
    }

    public void setSessionName(final String _sessionName) {
        this._sessionName = _sessionName;
    }
    @DynamoDBAttribute(attributeName = "sessionStudentList")
    public List<String> getSessionStudentList() {
        return _sessionStudentList;
    }

    public void setSessionStudentList(final List<String> _sessionStudentList) {
        this._sessionStudentList = _sessionStudentList;
    }

}

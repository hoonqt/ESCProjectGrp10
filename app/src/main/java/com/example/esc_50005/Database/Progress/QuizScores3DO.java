package com.example.esc_50005.Database.Progress;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-QuizScores3")

public class QuizScores3DO {
    private String _subjectCode;
    private String _studentID;
    private String _name;
    private String _quizName;
    private Double _score;
    private String _sessionID;

    @DynamoDBHashKey(attributeName = "SubjectCode")
    @DynamoDBAttribute(attributeName = "SubjectCode")
    public String getSubjectCode() {
        return _subjectCode;
    }

    public void setSubjectCode(final String _subjectCode) {
        this._subjectCode = _subjectCode;
    }
    @DynamoDBRangeKey(attributeName = "StudentID")
    @DynamoDBAttribute(attributeName = "StudentID")
    public String getStudentID() {
        return _studentID;
    }

    public void setStudentID(final String _studentID) {
        this._studentID = _studentID;
    }
    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return _name;
    }

    public void setName(final String _name) {
        this._name = _name;
    }
    @DynamoDBAttribute(attributeName = "QuizName")
    public String getQuizName() {
        return _quizName;
    }

    public void setQuizName(final String _quizName) {
        this._quizName = _quizName;
    }
    @DynamoDBAttribute(attributeName = "Score")
    public Double getScore() {
        return _score;
    }

    public void setScore(final Double _score) {
        this._score = _score;
    }
    @DynamoDBAttribute(attributeName = "SessionID")
    public String getSessionID() {
        return _sessionID;
    }

    public void setSessionID(final String _sessionID) {
        this._sessionID = _sessionID;
    }

}

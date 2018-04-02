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

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-QuizScores4")

public class QuizScores4DO {
    private String _courseID;
    private String _studentIDSessionID;
    private String _name;
    private String _quizName;
    private Double _score;

    @DynamoDBHashKey(attributeName = "CourseID")
    @DynamoDBIndexHashKey(attributeName = "CourseID", globalSecondaryIndexName = "SessionIDonly")
    public String getCourseID() {
        return _courseID;
    }

    public void setCourseID(final String _courseID) {
        this._courseID = _courseID;
    }
    @DynamoDBRangeKey(attributeName = "StudentIDSessionID")
    @DynamoDBIndexRangeKey(attributeName = "StudentIDSessionID", globalSecondaryIndexName = "SessionIDonly")
    public String getStudentIDSessionID() {
        return _studentIDSessionID;
    }

    public void setStudentIDSessionID(final String _studentIDSessionID) {
        this._studentIDSessionID = _studentIDSessionID;
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

}

package com.example.esc_50005.Database.QuizAnswers;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-QuizAnswers")

public class QuizAnswersDO {
    private String _subjectCodeSessionCode;
    private String _quizNameStudentID;
    private String _answer;
    private String _time;

    @DynamoDBHashKey(attributeName = "SubjectCodeSessionCode")
    @DynamoDBAttribute(attributeName = "SubjectCodeSessionCode")
    public String getSubjectCodeSessionCode() {
        return _subjectCodeSessionCode;
    }

    public void setSubjectCodeSessionCode(final String _subjectCodeSessionCode) {
        this._subjectCodeSessionCode = _subjectCodeSessionCode;
    }
    @DynamoDBRangeKey(attributeName = "QuizNameStudentID")
    @DynamoDBAttribute(attributeName = "QuizNameStudentID")
    public String getQuizNameStudentID() {
        return _quizNameStudentID;
    }

    public void setQuizNameStudentID(final String _quizNameStudentID) {
        this._quizNameStudentID = _quizNameStudentID;
    }
    @DynamoDBAttribute(attributeName = "Answer")
    public String getAnswer() {
        return _answer;
    }

    public void setAnswer(final String _answer) {
        this._answer = _answer;
    }
    @DynamoDBAttribute(attributeName = "Time")
    public String getTime() {
        return _time;
    }

    public void setTime(final String _time) {
        this._time = _time;
    }

}

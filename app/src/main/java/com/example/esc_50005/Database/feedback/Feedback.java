package com.example.esc_50005.Database.feedback;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by tan_j on 5/3/2018.
 */

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-Feedback")

public class Feedback {
    private String sessionId;
    private float rating;
    private String comment;
    private String author;
    private String studentId;

    @DynamoDBHashKey(attributeName = "sessionId")
    @DynamoDBAttribute(attributeName = "sessionId")
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    @DynamoDBAttribute(attributeName = "rating")
    public float getRating() {
        return rating;
    }
    public void setRating(final float rating) {
        this.rating = rating;
    }

    @DynamoDBAttribute(attributeName = "comment")
    public String getComment() {
        return comment;
    }
    public void setComment(final String comment) {
        this.comment = comment;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }
    public void setAuthor(final String author) {
        this.author = author;
    }

    @DynamoDBRangeKey(attributeName = "studentId")
    @DynamoDBAttribute(attributeName = "studentId")
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(final String studentId) {
        this.studentId = studentId;
    }
}

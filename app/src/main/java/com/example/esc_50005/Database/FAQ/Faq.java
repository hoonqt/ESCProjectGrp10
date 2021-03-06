package com.example.esc_50005.Database.FAQ;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-Faq")

@Entity (tableName = "faq")
public class Faq {
    @PrimaryKey
    @NonNull
    public String courseId;
    public String questionId;
    public String question;
    public String answer;
    public String date;
    public int upvotes;
    public String author;
    @TypeConverters(Converters.class)
    private ArrayList<String> usersVoted;

    @DynamoDBHashKey(attributeName = "courseId")
    @DynamoDBAttribute(attributeName = "courseId")
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(final String courseId) {
        this.courseId = courseId;
    }

    @DynamoDBRangeKey(attributeName = "questionId")
    @DynamoDBAttribute(attributeName = "questionId")
    public String getQuestionId() {
        return questionId;
    }
    public void setQuestionId(final String questionId) {
        this.questionId = questionId;
    }

    @DynamoDBAttribute(attributeName = "question")
    public String getQuestion() {
        return question;
    }
    public void setQuestion(final String question) {
        this.question = question;
    }

    @DynamoDBAttribute(attributeName = "date")
    public String getDate() {
        return date;
    }
    public void setDate(final String date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "answer")
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(final String answer) {
        this.answer = answer;
    }

    @DynamoDBAttribute(attributeName = "upvotes")
    public int getUpvotes() {
        return upvotes;
    }
    public void setUpvotes(final int _upvotes) {
        this.upvotes = _upvotes;
    }

    @DynamoDBAttribute(attributeName = "usersVoted")
    public ArrayList<String> getUsersVoted() {
        return usersVoted;
    }
    public void setUsersVoted(final ArrayList<String> usersVoted) {
        this.usersVoted = usersVoted;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

}

package com.example.cindy.esc_50005.Database.FAQ;

import java.util.Date;

public class Faq {

    private String courseID;
    private String question;
    private String answer;
    private int upvotes;
    private String author;
    private Date time;

    public Faq(String courseID,String question,String answer, String author) {

        this.courseID = courseID;
        this.question = question;
        this.answer = answer;
        upvotes = 0;
        this.author = author;
        time = new Date();

    }

    public void addOneUpvote() {
        upvotes++;
    }

    public Date getTime() {
        return time;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
}

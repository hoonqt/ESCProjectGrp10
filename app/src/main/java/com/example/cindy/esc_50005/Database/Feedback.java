package com.example.cindy.esc_50005.Database;

/**
 * Created by tan_j on 5/3/2018.
 */

public class Feedback {
    private String sessionId;
    private float rating;
    private String comments;
    private String author;

    public Feedback(String sessionId, float rating, String comments, String author) {
        this.sessionId = sessionId;
        this.rating = rating;
        this.comments = comments;
        this.author = author;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

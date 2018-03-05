package com.example.cindy.esc_50005.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tan_j on 5/3/2018.
 */

public class SessionFeedback {
    List<Feedback> sessionFeedback;

    public SessionFeedback() {
        this.sessionFeedback = new ArrayList<Feedback>();
    }

    public List<Feedback> getAllFeedback() {
        return sessionFeedback;
    }

    public void addFeedback(Feedback feedback) {
        sessionFeedback.add(feedback);
    }
}

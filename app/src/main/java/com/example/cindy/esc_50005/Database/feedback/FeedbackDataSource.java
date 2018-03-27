package com.example.cindy.esc_50005.Database.feedback;

import java.util.ArrayList;

/**
 * Created by Otter on 22/3/2018.
 */

public interface FeedbackDataSource {

    public void removeFeedback(Feedback feedback);

    public void saveFeedback(Feedback feedback);

    public ArrayList<Feedback> getFeedbackListBySessionId(String sessionId);
}

package com.example.cindy.esc_50005.Database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tan_j on 5/3/2018.
 */

public class FeedbackRepository {

    public static final String TAG = "FeedbackRepository";

    private HashMap<String, SessionFeedback> allFeedback;

    public FeedbackRepository() {
        this.allFeedback = new HashMap<>();
    }

    public void addFeedbackToSession(Feedback feedback) {
        String sessionId = feedback.getSessionId();
        SessionFeedback current;
        if (allFeedback.containsKey(sessionId)) {
            current = allFeedback.get(feedback.getSessionId());
        } else {
            current = new SessionFeedback();
        }
        current.addFeedback(feedback);
        allFeedback.put(feedback.getSessionId(), current);
        Log.i(TAG,"addfeedbacktosession");
    }

    private SessionFeedback getSessionFeedbackFromSessionId(String sessionId) {
        Log.i(TAG,"getsessionfeedback");
        return allFeedback.get(sessionId);
    }
}

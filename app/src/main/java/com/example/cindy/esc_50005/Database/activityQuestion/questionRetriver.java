package com.example.cindy.esc_50005.Database.activityQuestion;

import org.json.JSONObject;

/**
 * Created by hoonqt on 5/3/18.
 */

public class questionRetriver {

    JSONObject allQnsfromSession;



    public questionRetriver(String courseID, String sessionId) {

        questionCreator gatherer = new questionCreator();
        allQnsfromSession = gatherer.getQuestions(courseID, sessionId);

    }

    public void convertJSON() {




    }

}

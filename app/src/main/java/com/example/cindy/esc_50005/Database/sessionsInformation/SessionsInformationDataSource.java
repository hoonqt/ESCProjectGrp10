package com.example.cindy.esc_50005.Database.sessionsInformation;

import java.util.ArrayList;

/**
 * Created by cindy on 13/3/2018.
 */

public interface SessionsInformationDataSource {

    void removeSession(SessionsInformationDO faq);
    void addSession(final Double courseId, final String courseName);
    ArrayList<SessionsInformationDO> querySessions(Double sessionId, Double courseId);

}

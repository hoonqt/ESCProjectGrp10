package com.example.esc_50005.UI.Session.Main;

/**
 * Created by cindy on 13/3/2018.
 */

import com.example.esc_50005.Database.Database.SessionQuestionsDO;

/**
 * Created by cindy on 13/3/2018.
 */

public interface QuestionsItemListener {
    void onUpvoteClick(SessionQuestionsDO clickedQuestion);
    void onDownvoteClick(SessionQuestionsDO clickedQuestion);
    void onRetryClick();

}

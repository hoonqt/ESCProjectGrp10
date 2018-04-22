package com.example.esc_50005.UI.Session.Main;

/**
 * Created by cindy on 13/3/2018.
 */

import com.example.esc_50005.Database.Database.Question;

/**
 * Created by cindy on 13/3/2018.
 */

public interface QuestionsItemListener {
    void onUpvoteClick(Question clickedQuestion);
    void onDownvoteClick(Question clickedQuestion);
    void onRetryClick();

}

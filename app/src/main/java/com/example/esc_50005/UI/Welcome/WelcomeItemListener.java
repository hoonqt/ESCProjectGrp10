package com.example.esc_50005.UI.Welcome;

import com.example.esc_50005.Database.FAQ.Faq;

/**
 * Created by tan_j on 12/3/2018.
 */

public interface WelcomeItemListener {

    void onUpvoteClick(Faq clickedTask);
    void onDownvoteClick(Faq clickedTask);
    void onRetryClick();

}
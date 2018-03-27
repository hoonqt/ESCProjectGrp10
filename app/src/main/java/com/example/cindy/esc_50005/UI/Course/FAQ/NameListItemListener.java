package com.example.cindy.esc_50005.UI.Course.FAQ;

import com.example.cindy.esc_50005.Database.FAQ.Faq;

/**
 * Created by 1002215 on 27/3/18.
 */

public interface NameListItemListener {

    void onUpvoteClick(Faq clickedTask);
    void onDownvoteClick(Faq clickedTask);
    void onRetryClick();

}

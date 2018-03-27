package com.example.cindy.esc_50005.UI.Session.feedbackDialog;

import com.example.cindy.esc_50005.Database.feedback.Feedback;
import com.example.cindy.esc_50005.Database.feedback.FeedbackRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tan_j on 5/3/2018.
 */

public class FeedbackDialogPresenter implements FeedbackDialogContract.Presenter {

    public static final String TAG = "FeedbackDialogPresenter";

    private final FeedbackRemoteDataSource mFeedbackRepository;

    private final FeedbackDialogContract.View mFeedbackView;

//    public FeedbackDialogPresenter(@NonNull FeedbackRepository feedbackRepository,
//                             @NonNull FeedbackDialogContract.View feedbackView) {
//        mFeedbackRepository = checkNotNull(feedbackRepository, "tasksRepository cannot be null");;
//        mFeedbackView = checkNotNull(feedbackView, "tasksView cannot be null!");;
//        mFeedbackView.setPresenter(this);
//    }

    public FeedbackDialogPresenter(FeedbackDialogContract.View view) {
        mFeedbackRepository = new FeedbackRemoteDataSource();
        mFeedbackView = view;
        mFeedbackView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void addFeedback(float rating, String message) {
        Feedback newFeedback = new Feedback();
        newFeedback.setSessionId("1234");
        newFeedback.setAuthor("John");
        newFeedback.setComment(message);
        newFeedback.setRating(rating);
        newFeedback.setStudentId("1001688");

        mFeedbackRepository.saveFeedback(newFeedback);
        mFeedbackView.dismissDialog();

    }

    @Override
    public void onLaterClicked() {
        mFeedbackView.dismissDialog();
    }
}

package com.example.cindy.esc_50005.UI.Session.feedback;

import com.example.cindy.esc_50005.Database.Feedback;
import com.example.cindy.esc_50005.Database.FeedbackRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tan_j on 5/3/2018.
 */

public class FeedbackPresenter implements FeedbackContract.Presenter {

    public static final String TAG = "FeedbackPresenter";

    private final FeedbackRepository mFeedbackRepository;

    private final FeedbackContract.View mFeedbackView;

//    public FeedbackPresenter(@NonNull FeedbackRepository feedbackRepository,
//                             @NonNull FeedbackContract.View feedbackView) {
//        mFeedbackRepository = checkNotNull(feedbackRepository, "tasksRepository cannot be null");;
//        mFeedbackView = checkNotNull(feedbackView, "tasksView cannot be null!");;
//        mFeedbackView.setPresenter(this);
//    }

    public FeedbackPresenter(FeedbackContract.View view) {
        mFeedbackRepository = new FeedbackRepository();
        mFeedbackView = view;
        mFeedbackView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void onRatingSubmitted(float rating, String message) {

//        if (rating == 0) {
//            mRatingView.showMessage();
//            return;
//        }

//        if (!isRatingSecondaryActionShown) {
//            if (rating == 5) {
//                getMvpView().showPlayStoreRatingView();
//                getMvpView().hideSubmitButton();
//                getMvpView().disableRatingStars();
//            } else {
//                getMvpView().showRatingMessageView();
//            }
//            isRatingSecondaryActionShown = true;
//            return;
//        }

//        getMvpView().showMessage(R.string.rating_thanks);

        sendRatingDataToServerInBackground(rating);
        mFeedbackView.dismissDialog();

    }

    private void sendRatingDataToServerInBackground(float rating) {

        Feedback newFeedback = new Feedback("12345", rating, "empty comment", "empty author");
        mFeedbackRepository.addFeedbackToSession(newFeedback);
        mFeedbackView.showFeedbackSubmitted();
    }

    @Override
    public void onLaterClicked() {
        mFeedbackView.dismissDialog();
    }
}

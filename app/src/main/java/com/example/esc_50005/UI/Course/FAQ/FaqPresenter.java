package com.example.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Log;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cindy on 19/2/2018.
 */

public class FaqPresenter implements FaqContract.Presenter {

    public static final String TAG = "FaqPresenter";

    private final FaqContract.View mFaqView;
    public FaqRemoteDataSource mFaqRepository;
    ArrayList<Faq> mFaqList;

    String userId;
    String courseId;

    public FaqPresenter(@NonNull FaqRemoteDataSource faqRepository, @NonNull FaqContract.View faqView) {
        mFaqRepository = faqRepository;
        mFaqView = checkNotNull(faqView, "faqView cannot be null!");
        mFaqView.setPresenter(this);
    }

    @Override
    public void start() {

        loadFaq();
    }

    public void loadFaq() {

        mFaqList = mFaqRepository.getFaqListByCourseId(courseId);
        Log.i(TAG,"Getting mFaqList from courseId: " + courseId);
            processFaq(mFaqList);
        mFaqView.showLoadFaqError();
        Log.i(TAG, "mFaqList size is " + mFaqList.size());

    }

    public void processEmptyFaq() {
        mFaqView.showNoFaq();
    }

    public void upvoteFaq(Faq faq) {
        ArrayList<String> usersVoted = faq.getUsersVoted();
        if (!usersVoted.contains(userId)) {
//            faq.setUpvotes(faq.getUpvotes() + 1);
            usersVoted.add(userId);
            faq.setUsersVoted(usersVoted);
            mFaqRepository.saveFaq(faq);
            Log.i(TAG, "upvote Faq" + usersVoted.size());
        }
        loadFaq();
    }

    public void downvoteFaq(Faq faq) {
        ArrayList<String> usersVoted = faq.getUsersVoted();
        if (faq.getUsersVoted().contains(userId)) {
//            faq.setUpvotes(usersVoted.size() - 1);
            usersVoted.remove(userId);
            faq.setUsersVoted(usersVoted);
            mFaqRepository.saveFaq(faq);
            Log.i(TAG, "downvote Faq" + usersVoted.size());
        }
        loadFaq();
    }

    public void processFaq(ArrayList<Faq> mFaqList) {

        Log.i(TAG, "Length of mFaqList = " + mFaqList.size());

//        if (faqJsonData.size() != 0) {
            mFaqView.showFaq(mFaqList);
            mFaqView.faqLoaded();
//        }

    }

    @Override
    public void setUserId(String studentId) {
        this.userId = studentId;
    }

    @Override
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

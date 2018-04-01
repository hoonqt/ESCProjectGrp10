package com.example.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cindy on 19/2/2018.
 */

public class FaqPresenter implements FaqContract.Presenter {

    public static final String TAG = "SessionsPresenter";

    private final FaqContract.View mFaqView;
    public FaqRemoteDataSource mFaqRepository;
    ArrayList<Faq> faqJsonData;

    public FaqPresenter(@NonNull FaqContract.View faqView) {
        mFaqRepository = new FaqRemoteDataSource();
        mFaqView = checkNotNull(faqView, "faqView cannot be null!");
        mFaqView.setPresenter(this);
    }

    @Override
    public void start() {
        loadFaq();
    }

    public void loadFaq() {

        faqJsonData = mFaqRepository.getFaqListByCourseId("50003");

        if (faqJsonData.size()==0) {
            processEmptyFaq();
        } else {
            processFaq(faqJsonData);
        }

        Log.i(TAG, "LoadFaq size is " + faqJsonData.size());

    }

    public void processEmptyFaq() {
        mFaqView.showNoFaq();
    }

    public void upvoteFaq(Faq faq) {
        ArrayList<String> usersVoted = faq.getUsersVoted();
        if (!usersVoted.contains("1001688")) {
            faq.setUpvotes(faq.getUpvotes() + 1);
            usersVoted.add("1001688");
            faq.setUsersVoted(usersVoted);
            mFaqRepository.saveFaq(faq);
            Log.i(TAG, "upvote Faq" + faq.getUpvotes());
        }
        loadFaq();
    }

    public void downvoteFaq(Faq faq) {
        ArrayList<String> usersVoted = faq.getUsersVoted();
        if (faq.getUsersVoted().contains("1001688")) {
            faq.setUpvotes(faq.getUpvotes() - 1);
            usersVoted.remove("1001688");
            faq.setUsersVoted(usersVoted);
            mFaqRepository.saveFaq(faq);
            Log.i(TAG, "downvote Faq" + faq.getUpvotes());
        }
        loadFaq();
    }

    public void processFaq(ArrayList<Faq> faqJsonData) {

        Log.i(TAG, "Length of faqJsonData = " + faqJsonData.size());

        if (faqJsonData.size() != 0) {
            mFaqView.showFaq(faqJsonData);
            mFaqView.faqLoaded();
        }

    }

}

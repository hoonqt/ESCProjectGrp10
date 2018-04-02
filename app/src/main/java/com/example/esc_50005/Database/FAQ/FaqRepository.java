package com.example.esc_50005.Database.FAQ;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Otter on 28/3/2018.
 */

public class FaqRepository implements FaqDataSource {
    private static FaqRepository INSTANCE = null;

    private final FaqDataSource mFaqRemoteDataSource;

    private final FaqDataSource mFaqLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    ArrayList<Faq> mCachedFaqs;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private FaqRepository(@NonNull FaqDataSource faqRemoteDataSource,
                          @NonNull FaqDataSource faqLocalDataSource) {
        mFaqRemoteDataSource = checkNotNull(faqRemoteDataSource);
        mFaqLocalDataSource = checkNotNull(faqLocalDataSource);
    }

    private FaqRepository() {
        mFaqLocalDataSource = new FaqRemoteDataSource();
        mFaqRemoteDataSource = new FaqRemoteDataSource();
    }
    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param faqRemoteDataSource the backend data source
     * @param faqLocalDataSource  the device storage data source
     * @return the {@link FaqRepository} instance
     */
    public static FaqRepository getInstance(FaqDataSource faqRemoteDataSource,
                                              FaqDataSource faqLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FaqRepository(faqRemoteDataSource, faqLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(FaqDataSource, FaqDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public ArrayList<Faq> getFaqListByCourseId (String courseId) {

        // Respond immediately with cache if available and not dirty
        if (mCachedFaqs != null && !mCacheIsDirty) {
            return mCachedFaqs;
        }

        ArrayList<Faq> faqs;
        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            faqs = mFaqRemoteDataSource.getFaqListByCourseId(courseId);
        } else {
            // Query the local storage if available. If not, query the network.
            faqs = mFaqLocalDataSource.getFaqListByCourseId(courseId);
        }
        refreshCache(faqs);
        refreshLocalDataSource(faqs);
        return faqs;
    }

    @Override
    public void saveFaq(@NonNull Faq faq) {
        checkNotNull(faq);
        mFaqRemoteDataSource.saveFaq(faq);
        mFaqLocalDataSource.saveFaq(faq);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedFaqs == null) {
            mCachedFaqs = new ArrayList<>();
        }

        mCachedFaqs.add(faq);
    }

    public void refreshFaqs() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteFaq(Faq faq) {

        mFaqRemoteDataSource.deleteFaq(faq);
        mFaqLocalDataSource.deleteFaq(faq);

        mCachedFaqs.remove(faq);
    }

//    @Override
//    public void deleteAllFaqs() {
//
//    }
//
//    private void getFaqsFromRemoteDataSource(String courseId) {
//        ArrayList<Faq> faqs = mFaqRemoteDataSource.getFaqListByCourseId(courseId);
//        refreshCache(faqs);
//        refreshLocalDataSource(faqs);
//    }

    private void refreshCache(List<Faq> faqs) {
        if (mCachedFaqs == null) {
            mCachedFaqs = new ArrayList<>();
        }
        mCachedFaqs.clear();
        for (Faq faq : faqs) {
            mCachedFaqs.add(faq);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Faq> faqs) {
//        mFaqLocalDataSource.deleteAllFaqs();
        for (Faq faq : faqs) {
            mFaqLocalDataSource.saveFaq(faq);
        }
    }

//    public ArrayList<Faq> getFaqListByCourseId(String courseId) {
////        checkNotNull(courseId);
//        if (mCachedFaqs == null || mCachedFaqs.isEmpty()) {
//            return null;
//        } else {
//            return mCachedFaqs.get(courseId);
//        }
//    }
}

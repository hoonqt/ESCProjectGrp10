//package com.example.cindy.esc_50005.Database.FAQ;
//
//import java.util.List;
//
///**
// * Created by Otter on 28/3/2018.
// */
//
//public class FaqLocalDataSource implements FaqDataSource {
//    private static volatile FaqLocalDataSource INSTANCE;
//
//    private FaqDao mFaqDao;
//
//    // Prevent direct instantiation.
//    private FaqLocalDataSource(FaqDao faqDao) {
//        mFaqDao = faqDao;
//    }
//
//    public static FaqLocalDataSource getInstance(FaqDao faqDao) {
//        if (INSTANCE == null) {
//            synchronized (FaqLocalDataSource.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new FaqLocalDataSource(faqDao);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    @Override
//    public void getFaq(@NonNull final String faqId, @NonNull final GetFaqCallback callback) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                final Faq faq = mFaqDao.getFaqById(faqId);
//
//                mAppExecutors.mainThread().execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (faq != null) {
//                            callback.onFaqLoaded(faq);
//                        } else {
//                            callback.onDataNotAvailable();
//                        }
//                    }
//                });
//            }
//        };
//
//        mAppExecutors.diskIO().execute(runnable);
//    }
//
//    @Override
//    public void saveFaq(@NonNull final Faq faq) {
//        checkNotNull(faq);
//        Runnable saveRunnable = new Runnable() {
//            @Override
//            public void run() {
//                mFaqDao.insertFaq(faq);
//            }
//        };
//        mAppExecutors.diskIO().execute(saveRunnable);
//    }
//
//    @Override
//    public void deleteFaq(final Faq faq) {
//        Runnable deleteRunnable = new Runnable() {
//            @Override
//            public void run() {
//                mFaqDao.delete(faq);
//            }
//        };
//    }
//
//    @Override
//    public void deleteFaq(final Faq faq) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mFaqDao.delete(faq);
//            }
//        }) .start();
//    }
//
//    @VisibleForTesting
//    static void clearInstance() {
//        INSTANCE = null;
//    }
//
//}

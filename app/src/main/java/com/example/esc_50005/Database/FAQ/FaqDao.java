package com.example.esc_50005.Database.FAQ;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Otter on 28/3/2018.
 */

@Dao
public interface FaqDao {

    @Query("SELECT * FROM faq WHERE faq.courseId = :courseId")
    List<Faq> getFaqListByCourseId(String courseId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Faq faq);

    //    @Query("DELETE FROM faq WHERE entryid = :questionId")
//    void deleteFaq(String questionId);
    @Delete
    void delete(Faq faq);
}

package com.example.cindy.esc_50005.Database.CoursesInformation;

import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;

import java.util.ArrayList;

/**
 * Created by cindy on 13/3/2018.
 */

public interface CoursesInformationDataSource {

    void removeCourse(CoursesInformationDO faq);
    void addCourse(final Double courseId, final String courseName);
    ArrayList<CoursesInformationDO> queryCourses(Double courseId, String courseName);

}

package com.example.esc_50005.Database.CoursesInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-CoursesInformation")

public class CoursesInformationDO {
    private String _courseId;
    private String _courseName;
    private List<String> _courseStudentList;

    @DynamoDBHashKey(attributeName = "courseId")
    @DynamoDBAttribute(attributeName = "courseId")
    public String getCourseId() {
        return _courseId;
    }

    public void setCourseId(final String _courseId) {
        this._courseId = _courseId;
    }
    @DynamoDBRangeKey(attributeName = "courseName")
    @DynamoDBAttribute(attributeName = "courseName")
    public String getCourseName() {
        return _courseName;
    }

    public void setCourseName(final String _courseName) {
        this._courseName = _courseName;
    }
    @DynamoDBAttribute(attributeName = "courseStudentList")
    public List<String> getCourseStudentList() {
        return _courseStudentList;
    }

    public void setCourseStudentList(final List<String> _courseStudentList) {
        this._courseStudentList = _courseStudentList;
    }

}

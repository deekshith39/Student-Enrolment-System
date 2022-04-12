package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.response.DisplayCoursesResponse;

public interface CourseService {
    public DisplayCoursesResponse displayCourses() throws DataBaseException;
}

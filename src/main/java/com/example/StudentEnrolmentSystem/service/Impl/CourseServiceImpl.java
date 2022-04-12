package com.example.StudentEnrolmentSystem.service.Impl;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.Course;
import com.example.StudentEnrolmentSystem.model.response.DisplayCoursesResponse;
import com.example.StudentEnrolmentSystem.repository.CourseRepository;
import com.example.StudentEnrolmentSystem.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public DisplayCoursesResponse displayCourses() throws DataBaseException {
        List<Course> courses;

        try {
            courses = courseRepository.findAll();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        DisplayCoursesResponse coursesResponse = new DisplayCoursesResponse();
        coursesResponse.setCoursesOffered(courses);

        return coursesResponse;
    }
}

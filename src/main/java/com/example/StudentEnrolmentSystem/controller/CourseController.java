package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.response.DisplayCoursesResponse;
import com.example.StudentEnrolmentSystem.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * This API will fetch all the courses offered.
     *
     * @return list of course objects
     * @throws DataBaseException
     */
    @GetMapping("/display_courses")
    @ApiOperation(value = "Find all courses",
    notes = "Provides all the courses offered by the college",
    response = DisplayCoursesResponse.class)
    public DisplayCoursesResponse displayCourses() throws DataBaseException {
        return courseService.displayCourses();
    }
}

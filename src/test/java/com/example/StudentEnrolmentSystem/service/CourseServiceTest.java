package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.Course;
import com.example.StudentEnrolmentSystem.model.response.DisplayCoursesResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import com.example.StudentEnrolmentSystem.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @MockBean
    CourseRepository courseRepository;

    List<Course> courses = new ArrayList<>();

    @BeforeEach
    void setUp() {

        Course course = Course.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        courses.add(course);
    }

    @Test
    void displayCourses() throws DataBaseException {
        Mockito.when(courseRepository.findAll()).thenReturn(courses);

        DisplayCoursesResponse found = courseService.displayCourses();

        assertEquals(found.getCoursesOffered().stream().count(), 1);

    }

}
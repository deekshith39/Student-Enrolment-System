package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.Course;
import com.example.StudentEnrolmentSystem.model.response.DisplayCoursesResponse;
import com.example.StudentEnrolmentSystem.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CourseControllerTest {

    @MockBean
    CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    DisplayCoursesResponse coursesResponse;

    @BeforeEach
    void setUp() {

        List<Course> courses = new ArrayList<>();

        Course course = Course.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        courses.add(course);

        coursesResponse = new DisplayCoursesResponse();
        coursesResponse.setCoursesOffered(courses);
    }

    @Test
    void displayCourses() throws Exception {
        Mockito.when(courseService.displayCourses()).thenReturn(coursesResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/course/display_courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
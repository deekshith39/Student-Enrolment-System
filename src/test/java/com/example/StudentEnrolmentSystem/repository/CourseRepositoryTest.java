package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.Course;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private TestEntityManager entityManager; // For persistent data

    @BeforeEach
    void setUp() {
        Course course = Course.builder()
                .courseId("BMSMECH")
                .courseName("Mechanical Engineering")
                .build();

        entityManager.persist(course);
    }

    @Test
    public void fetchCourses() {
        List<String> courseIds = courseRepository.fetchCourses();
        assertEquals(courseIds.stream().count(), 1);

    }
}
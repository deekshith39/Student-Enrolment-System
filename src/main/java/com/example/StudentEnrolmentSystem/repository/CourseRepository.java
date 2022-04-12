package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    /**
     * This method queries for the courses offered from the course table
     * @return List of courses
     */
    @Query(value = "select course_id from course", nativeQuery = true)
    List<String> fetchCourses();
}

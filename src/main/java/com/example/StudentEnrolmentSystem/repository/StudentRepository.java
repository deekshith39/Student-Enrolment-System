package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    /**
     *
     * This method queries for the number of students enrolled in a course.
     * @param courseId
     * @return count
     */
    @Query(value = "select count(usn) from student where course_id=?1 group by course_id", nativeQuery = true)
    int countByCourse(String courseId);

}

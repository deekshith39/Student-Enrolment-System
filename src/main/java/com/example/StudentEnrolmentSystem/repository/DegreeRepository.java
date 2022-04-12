package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, String> {
    /**
     * This method queries for the degrees offered from the degree table
     * @return List of degrees
     */
    @Query(value = "select degree_id from degree", nativeQuery = true)
    List<String> fetchDegrees();
}

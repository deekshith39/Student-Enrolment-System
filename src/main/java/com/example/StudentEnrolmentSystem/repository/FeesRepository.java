package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeesRepository extends JpaRepository<Fees, String> {
}

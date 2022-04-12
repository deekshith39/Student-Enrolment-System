package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.Degree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DegreeRepositoryTest {

    @Autowired
    DegreeRepository degreeRepository;

    @Autowired
    private TestEntityManager entityManager; // For persistent data

    @BeforeEach
    void setUp() {
        Degree degree = Degree.builder()
                .degreeId("BMSDIP")
                .degreeName("Diploma")
                .duration(2)
                .build();

        entityManager.persist(degree);
    }

    @Test
    void fetchDegrees() {
        List<String> degreeIds = degreeRepository.fetchDegrees();
        assertEquals(degreeIds.stream().count(), 1);

    }

}
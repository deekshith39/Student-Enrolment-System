package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.Fees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FeesRepositoryTest {

    @Autowired
    private FeesRepository feesRepository;

    @Autowired
    private TestEntityManager entityManager; // For persistent data

    @BeforeEach
    void setUp() {
        Degree degree = Degree.builder()
                .degreeId("BMSPG")
                .degreeName("Post Graduation")
                .duration(4)
                .build();

        Fees fees = Fees.builder()
                .examFee(5423.5F)
                .universityFee(521290.45F)
                .tuitionFee(3450.3F)
                .degree(degree)
                .build();

        entityManager.persist(degree);
        entityManager.persist(fees);
    }

    @Test
    void whenFindByDegreeId_thenReturnFees() {
        Fees fees = feesRepository.findById("BMSPG").get();
        assertEquals(fees.getExamFee(), 5423.5);
    }

}
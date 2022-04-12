package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.response.DisplayDegreeResponse;
import com.example.StudentEnrolmentSystem.repository.DegreeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DegreeServiceTest {

    @Autowired
    DegreeService degreeService;

    @MockBean
    DegreeRepository degreeRepository;

    List<Degree> degrees = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Degree degree = Degree.builder()
                .degreeId("BMSBE")
                .degreeName("Bachelor of Engineering")
                .duration(4)
                .build();

        degrees.add(degree);
    }

    @Test
    void displayDegrees() throws DataBaseException {
        Mockito.when(degreeRepository.findAll()).thenReturn(degrees);

        DisplayDegreeResponse found = degreeService.displayDegrees();

        assertEquals(found.getDegreesOffered().stream().count(), 1);
    }
}
package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.response.DisplayDegreeResponse;
import com.example.StudentEnrolmentSystem.service.DegreeService;
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
class DegreeControllerTest {

    @MockBean
    DegreeService degreeService;

    @Autowired
    private MockMvc mockMvc;

    DisplayDegreeResponse degreeResponse;

    @BeforeEach
    void setUp() {
        List<Degree> degrees = new ArrayList<>();

        Degree degree = Degree.builder()
                .degreeId("BMSBE")
                .degreeName("Bachelor of Engineering")
                .duration(4)
                .build();

        degrees.add(degree);

        degreeResponse = new DisplayDegreeResponse();
        degreeResponse.setDegreesOffered(degrees);
    }

    @Test
    void displayDegrees() throws Exception {
        Mockito.when(degreeService.displayDegrees()).thenReturn(degreeResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/degree/display_degrees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
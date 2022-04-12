package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.model.*;
import com.example.StudentEnrolmentSystem.model.request.StudentRequest;
import com.example.StudentEnrolmentSystem.model.response.AddStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import com.example.StudentEnrolmentSystem.service.StudentService;
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

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @MockBean
    StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    AddStudentResponse studentResponse;

    StudentRequest studentInput;

    DisplayStudentResponse studentOutput;

    @BeforeEach
    void setUp() {
        studentResponse = AddStudentResponse.builder()
                .usn("BMSBECS001")
                .tuitionFees(1000)
                .universityFees(2000)
                .examFees(2000)
                .totalFees(5000)
                .build();

        studentInput = StudentRequest.builder()
                .name("Rajesh")
                .dob("12/03/2000")
                .email("rajesh@gmail.com")
                .gender("Male")
                .contactNumber("9011111234")
                .address("Bangalore")
                .classTenBoard("CBSE")
                .classTenPercentage(80.34f)
                .classTwelveBoard("STATE")
                .classTwelvePercentage(85.45f)
                .fatherName("Prakash")
                .fatherContactNumber("8723123412")
                .motherName("Rani")
                .courseId("BMSCS")
                .degreeId("BMSBE")
                .password("rajesh@2020")
                .build();

        Course course = Course.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        Degree degree = Degree.builder()
                .degreeId("BMSBE")
                .degreeName("Bachelor of Engineering")
                .build();

        studentOutput = DisplayStudentResponse.builder()
                .name("Rajesh")
                .dob("12/03/2000")
                .email("rajesh@gmail.com")
                .gender("Male")
                .contactNumber("9011111234")
                .address("Bangalore")
                .classTenBoard("CBSE")
                .classTenPercentage(80.34f)
                .classTwelveBoard("STATE")
                .classTwelvePercentage(85.45f)
                .fatherName("Prakash")
                .fatherContactNumber("8723123412")
                .motherName("Rani")
                .course(course)
                .degree(degree)
                .build();

    }

    @Test
    void registerStudent() throws Exception {

        Mockito.when(studentService.registerStudent(studentInput)).thenReturn(studentResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/student/add_student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Siri\",\n" +
                                "    \"dob\": \"12/02/2000\",\n" +
                                "    \"email\": \"suresh@gmail.com\",\n" +
                                "    \"gender\": \"male\",\n" +
                                "    \"contactNumber\": \"9023123789\",\n" +
                                "    \"address\": \"Bangalore\",\n" +
                                "    \"classTenBoard\": \"CBSE\",\n" +
                                "    \"classTenPercentage\": 91.1,\n" +
                                "    \"classTwelveBoard\": \"STATE\",\n" +
                                "    \"classTwelvePercentage\": 89.3,\n" +
                                "    \"fatherName\": \"Mahesh\",\n" +
                                "    \"fatherContactNumber\": \"9080092133\",\n" +
                                "    \"motherName\": \"Navya\",\n" +
                                "    \"courseId\": \"BMSME\",\n" +
                                "    \"degreeId\": \"BMSPG\",\n" +
                                "    \"password\": \"siri@2020\"\n" +
                                "}\n"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void displayDetails() throws Exception {
        Mockito.when(studentService.displayDetails()).thenReturn(studentOutput);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/display_details")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(studentOutput.getName()));
    }
}
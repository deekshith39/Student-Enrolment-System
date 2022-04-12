package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.model.Course;
import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.Fees;
import com.example.StudentEnrolmentSystem.model.dto.CourseDTO;
import com.example.StudentEnrolmentSystem.model.dto.FeesDTO;
import com.example.StudentEnrolmentSystem.model.response.AddCourseResponse;
import com.example.StudentEnrolmentSystem.model.response.DeleteStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import com.example.StudentEnrolmentSystem.service.AdminService;
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

import java.sql.Timestamp;
import java.time.Instant;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {

    @MockBean
    AdminService adminService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchStudent() throws Exception {
        Course course = Course.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        Degree degree = Degree.builder()
                .degreeId("BMSBE")
                .degreeName("Bachelor of Engineering")
                .build();

        DisplayStudentResponse studentResponse = DisplayStudentResponse.builder()
                .userId("BMSBECS001")
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

        Mockito.when(adminService.fetchStudentById("BMSBECS001")).thenReturn(studentResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/search_student/BMSBECS001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(studentResponse.getName()));

    }

    @Test
    void deleteStudent() throws Exception {
        String usn = "BMSBECS001";
        DeleteStudentResponse studentResponse = DeleteStudentResponse.builder()
                .usn(usn)
                .message("User made as inactive")
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        Mockito.when(adminService.deleteStudentById(usn)).thenReturn(studentResponse);

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/delete_student/BMSBECS001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addCourse() throws Exception {
        AddCourseResponse courseResponse = AddCourseResponse.builder()
                .courseCode("BMSCS")
                .timestamp(Timestamp.from(Instant.now()))
                .message("course added")
                .build();

        CourseDTO course = CourseDTO.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        Mockito.when(adminService.addCourse(course)).thenReturn(courseResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/add_course")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"courseId\": \"BMSBT\",\n" +
                        "    \"courseName\": \"Bio Technology\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateFees() throws Exception {
        String degreeId = "BMSBE";
        FeesDTO feesRequest = FeesDTO.builder()
                .examFee(1234.5F)
                .build();

        FeesDTO fees = FeesDTO.builder()
                .degreeId(degreeId)
                .examFee(1234.5F)
                .tuitionFee(11112.3F)
                .universityFee(5432.22F)
                .build();

        Mockito.when(adminService.updateFees(degreeId, feesRequest)).thenReturn(fees);

        mockMvc.perform(MockMvcRequestBuilders.put("/admin/update_fees/BMSBE")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"examFee\": 1234.5\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
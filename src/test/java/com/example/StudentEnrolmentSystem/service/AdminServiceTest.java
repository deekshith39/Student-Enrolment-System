package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.errorHandling.NotFoundException;
import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.Course;
import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.Fees;
import com.example.StudentEnrolmentSystem.model.Student;
import com.example.StudentEnrolmentSystem.model.dto.CourseDTO;
import com.example.StudentEnrolmentSystem.model.dto.FeesDTO;
import com.example.StudentEnrolmentSystem.model.response.AddCourseResponse;
import com.example.StudentEnrolmentSystem.model.response.DeleteStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import com.example.StudentEnrolmentSystem.repository.CourseRepository;
import com.example.StudentEnrolmentSystem.repository.FeesRepository;
import com.example.StudentEnrolmentSystem.repository.StudentRepository;
import com.example.StudentEnrolmentSystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    CourseRepository courseRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    FeesRepository feesRepository;

    Student student;

    Course course;

    Fees fees;

    @BeforeEach
    void setUp() {
        course = Course.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        Degree degree = Degree.builder()
                .degreeId("BMSBE")
                .degreeName("Bachelor of Engineering")
                .build();

        student = Student.builder()
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

        fees = Fees.builder()
                .examFee(5423.5F)
                .universityFee(521290.45F)
                .tuitionFee(3450.3F)
                .degree(degree)
                .build();
    }

    @Test
    void whenValidUsn_thenReturnStudent() throws NotFoundException, DataBaseException {
        String usn = "BMSBECS001";
        Mockito.when(studentRepository.existsById(usn)).thenReturn(true);
        Mockito.when(studentRepository.findById(usn)).thenReturn(Optional.ofNullable(student));

        DisplayStudentResponse found = adminService.fetchStudentById(usn);

        assertEquals(found.getUserId(), usn);
    }

    @Test
    void whenAddValidCourse_thenReturnSuccessResponse() throws ValidationUnsuccessfulException, DataBaseException {
        CourseDTO courseDTO = CourseDTO.builder()
                .courseId("BMSCS")
                .courseName("Computer Science")
                .build();

        Mockito.when(courseRepository.save(course)).thenReturn(course);

        AddCourseResponse found = adminService.addCourse(courseDTO);
        assertEquals(found.getCourseCode(), "BMSCS");
    }

    @Test
    void whenDeleteStudent_thenReturnSuccessResponse() throws NotFoundException, DataBaseException {
        String usn = "BMSBECS001";
        Mockito.when(userRepository.existsById(usn)).thenReturn(true);

        DeleteStudentResponse found = adminService.deleteStudentById(usn);

        assertEquals(found.getUsn(), usn);
    }

    @Test
    void whenUpdateFees_thenReturnSuccessResponse() throws NotFoundException, DataBaseException {

        FeesDTO feesRequest = FeesDTO.builder()
                .examFee(1234.5F)
                .build();

        String degreeId = "BMSBE";
        Mockito.when(feesRepository.existsById(degreeId)).thenReturn(true);
        Mockito.when(feesRepository.findById(degreeId)).thenReturn(Optional.ofNullable(fees));
        Mockito.when(feesRepository.save(fees)).thenReturn(fees);
        Mockito.when(feesRepository.getById(degreeId)).thenReturn(fees);

        FeesDTO found = adminService.updateFees(degreeId, feesRequest);
        assertEquals(found.getExamFee(), 1234.5F);
    }
}
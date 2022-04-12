package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.*;
import com.example.StudentEnrolmentSystem.model.request.StudentRequest;
import com.example.StudentEnrolmentSystem.model.response.AddStudentResponse;
import com.example.StudentEnrolmentSystem.repository.FeesRepository;
import com.example.StudentEnrolmentSystem.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    FeesRepository feesRepository;

    StudentRequest studentInput;

    Student student;

    Fees fees;

    @BeforeEach
    void setUp() {
        Course course = Course.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        Degree degree = Degree.builder()
                .degreeId("BMSBE")
                .degreeName("Bachelor of Engineering")
                .build();

        User user = User.builder()
                .password("123456")
                .role("USER")
                .build();

        student = Student.builder()
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
                .user(user)
                .course(course)
                .degree(degree)
                .build();

        fees = Fees.builder()
                .examFee(1000)
                .universityFee(2000)
                .tuitionFee(2000)
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
    }

    @Test
    void whenValidStudent_thenReturnStudentResponse() throws Exception {

        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when(studentRepository.countByCourse("BMSCS")).thenReturn(0);
        Mockito.when(feesRepository.findById("BMSBE")).thenReturn(Optional.ofNullable(fees));

        AddStudentResponse found = studentService.registerStudent(studentInput);
        assertEquals(found.getTotalFees(), 5000);
    }

    @Test
    void whenNameisNull_thenThrowException() {
        studentInput.setName(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Name is required and expected");
    }

    @Test
    void whenDobisNull_thenThrowException() {
        studentInput.setDob(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "DOB is required and expected");
    }

    @Test
    void whenInvalidDate_thenThrowException() {
        studentInput.setDob("111/11/11");
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "DOB required format: dd/MM/yyyy");
    }

    @Test
    void whenEmailNull_thenThrowException() {
        studentInput.setEmail(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Email is a required field.");

    }

    @Test
    void whenEmailFormatInvalid_thenThrowException() {
        studentInput.setEmail("rajeshgmail.com");
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Invalid Email format");
    }

    @Test
    void whenGenderNull_thenThrowException() {
        studentInput.setGender(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Gender is a required field");
    }

    @Test
    void contactNumberisNull_thenThrowExeception() {
        studentInput.setContactNumber(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Phone Number is a required field");
    }

    @Test
    void whenInvalidContactNumber_thenThrowException() {
        studentInput.setContactNumber("abcd342");
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Phone Number should be 10 digit number");
    }

    @Test
    void whenAddressNull_thenThrowException() {
        studentInput.setAddress(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Empty Address should throw exception");
    }

    @Test
    void whenClassTenBoardNull_thenThrowException() {
        studentInput.setClassTenBoard(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Class Ten Board is required");
    }

    @Test
    void whenClassTenBoardInvalid_thenThrowException() {
        studentInput.setClassTenBoard("abcd");
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Invalid class ten board should throw error");
    }

    @Test
    void whenClassTwelveBoardNull_thenThrowException() {
        studentInput.setClassTwelveBoard(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "class twelve board is required");
    }

    @Test
    void whenFatherNameNull_thenThrowException() {
        studentInput.setFatherName(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Father name is required");
    }

    @Test
    void whenFatherContactNumberInvalid_thenThrowException() {
        studentInput.setFatherContactNumber(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Invalid Contact number should throw exception");
    }

    @Test
    void whenMotherNameNull_thenThrowException() {
        studentInput.setMotherName(null);
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Empty mother name should throw an exception");
    }

    @Test
    void whenPasswordInvalid_thenThrowException() {
        studentInput.setPassword("abcd");
        Assertions.assertThrows(ValidationUnsuccessfulException.class, () -> studentService.registerStudent(studentInput), "Password should atleast 6 characters");
    }

}
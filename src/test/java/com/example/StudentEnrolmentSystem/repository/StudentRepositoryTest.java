package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.Course;
import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.Student;
import com.example.StudentEnrolmentSystem.model.User;
import com.example.StudentEnrolmentSystem.util.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager; // For persistent data

    Generator usnGenerator = new Generator();

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() throws DataBaseException {
        Course course = Course.builder()
                .courseId("BMSCS")
                .courseName("Computer Science Engineering")
                .build();

        Degree degree = Degree.builder()
                .degreeId("BMSBE")
                .degreeName("Bachelor of Engineering")
                .build();

        User user = User.builder()
                .userId(usnGenerator.generateUsn(course.getCourseId(), degree.getDegreeId()))
                .password(passwordEncoder.encode("12345"))
                .role("USER")
                .status("ACTIVE")
                .build();

        Student student = Student.builder()
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

        entityManager.persist(course);
        entityManager.persist(degree);
        entityManager.persist(student);

    }

    @Test
    void whenFindById_thenReturnStudent() {
        Student student = studentRepository.findById("BMSBECS001").get();
        assertEquals(student.getName(), "Rajesh");
    }

    @Test
    void whenCountByCourse_thenReturnCourseCount() {
        int count = studentRepository.countByCourse("BMSCS");
        assertEquals(count, 1);
    }
}
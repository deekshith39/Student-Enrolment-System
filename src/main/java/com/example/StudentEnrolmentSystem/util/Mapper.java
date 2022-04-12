package com.example.StudentEnrolmentSystem.util;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.*;
import com.example.StudentEnrolmentSystem.model.dto.FeesDTO;
import com.example.StudentEnrolmentSystem.model.request.StudentRequest;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    Generator generator;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Student addStudentMapper(StudentRequest student) throws DataBaseException {

        Student saveStudent = new Student();

        saveStudent.setName(student.getName());
        saveStudent.setDob(student.getDob());
        saveStudent.setEmail(student.getEmail());
        saveStudent.setGender(student.getGender());
        saveStudent.setContactNumber(student.getContactNumber());
        saveStudent.setAddress(student.getAddress());
        saveStudent.setClassTenBoard(student.getClassTenBoard());
        saveStudent.setClassTenPercentage(student.getClassTenPercentage());
        saveStudent.setClassTwelveBoard(student.getClassTwelveBoard());
        saveStudent.setClassTwelvePercentage(student.getClassTwelvePercentage());
        saveStudent.setFatherName(student.getFatherName());
        saveStudent.setFatherContactNumber(student.getFatherContactNumber());
        saveStudent.setMotherName(student.getMotherName());

        Course course = new Course();
        course.setCourseId(student.getCourseId());
        saveStudent.setCourse(course);

        Degree degree = new Degree();
        degree.setDegreeId(student.getDegreeId());
        saveStudent.setDegree(degree);

        User user = new User();
        String courseId = student.getCourseId();
        String degreeId = student.getDegreeId();
        String usn = generator.generateUsn(courseId, degreeId);

        user.setUserId(usn);
        user.setPassword(passwordEncoder.encode(student.getPassword()));
        user.setRole("USER");
        user.setStatus("ACTIVE");
        saveStudent.setUser(user);

        return saveStudent;

    }

    public DisplayStudentResponse displayStudentResponse(Student student) {

        DisplayStudentResponse studentResponse = new DisplayStudentResponse();

        studentResponse.setName(student.getName());
        studentResponse.setDob(student.getDob());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setGender(student.getGender());
        studentResponse.setContactNumber(student.getContactNumber());
        studentResponse.setAddress(student.getAddress());
        studentResponse.setClassTenBoard(student.getClassTenBoard());
        studentResponse.setClassTenPercentage(student.getClassTenPercentage());
        studentResponse.setClassTwelveBoard(student.getClassTwelveBoard());
        studentResponse.setClassTwelvePercentage(student.getClassTwelvePercentage());
        studentResponse.setFatherName(student.getFatherName());
        studentResponse.setFatherContactNumber(student.getFatherContactNumber());
        studentResponse.setMotherName(student.getMotherName());
        studentResponse.setCourse(student.getCourse());
        studentResponse.setDegree(student.getDegree());
        studentResponse.setUserId(student.getUserId());

        return studentResponse;

    }

    public FeesDTO updateFeesResponse(Fees fees) {
        FeesDTO feesDTO = new FeesDTO();

        feesDTO.setDegreeId(fees.getDegreeId());
        feesDTO.setExamFee(fees.getExamFee());
        feesDTO.setTuitionFee(fees.getTuitionFee());
        feesDTO.setUniversityFee(fees.getUniversityFee());

        return feesDTO;
    }

}

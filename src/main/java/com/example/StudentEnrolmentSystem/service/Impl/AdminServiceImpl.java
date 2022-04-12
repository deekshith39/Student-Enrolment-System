package com.example.StudentEnrolmentSystem.service.Impl;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.errorHandling.NotFoundException;
import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.Course;
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
import com.example.StudentEnrolmentSystem.service.AdminService;
import com.example.StudentEnrolmentSystem.util.ErrorConstants;
import com.example.StudentEnrolmentSystem.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    FeesRepository feesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Mapper mapper;

    @Override
    public DisplayStudentResponse fetchStudentById(String usn) throws NotFoundException, DataBaseException {

        Student student;

        Boolean isExists;

        try {
            isExists = studentRepository.existsById(usn);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        if(isExists) {
            try {
                student = studentRepository.findById(usn).get();
            } catch (Exception ex) {
                throw new DataBaseException("Database Exception");
            }
        } else {
            throw new NotFoundException("Student not found");
        }

        return mapper.displayStudentResponse(student);
    }

    @Override
    public DeleteStudentResponse deleteStudentById(String usn) throws NotFoundException, DataBaseException {

        Boolean isExists;

        try {
            isExists = studentRepository.existsById(usn);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        if(isExists) {
            try {
                userRepository.updateUserStatus(usn);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                throw new DataBaseException("Database Error");
            }
        } else {
            throw new NotFoundException("User not found");
        }

        return new DeleteStudentResponse(Timestamp.from(Instant.now()), usn, "Student set as inactive");
    }

    @Override
    public AddCourseResponse addCourse(CourseDTO course) throws ValidationUnsuccessfulException, DataBaseException {
        if(course.getCourseId() == null || course.getCourseId() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.COURSEID_REQUIRED);
        }

        if(course.getCourseName() == null || course.getCourseName() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.COURSE_NAME_REQUIRED);
        }

        Course sCourse = new Course();
        sCourse.setCourseId(course.getCourseId());
        sCourse.setCourseName(course.getCourseName());

        try {
            courseRepository.save(sCourse);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        return new AddCourseResponse(Timestamp.from(Instant.now()), course.getCourseId(), "Course added successfully");
    }

    @Override
    public FeesDTO updateFees(String degreeId, FeesDTO fees) throws NotFoundException, DataBaseException {

        Boolean isExists;

        try {
            isExists = feesRepository.existsById(degreeId);
        } catch (Exception ex) {
            throw new DataBaseException("Database Error");
        }

        if(isExists) {
            Fees uFees;

            try {
                uFees = feesRepository.findById(degreeId).get();
            } catch (Exception ex) {
                log.error(ex.getMessage());
                throw new DataBaseException("Database Error");
            }

            if(fees.getExamFee() != 0 && fees.getExamFee() != uFees.getExamFee()) {
                uFees.setExamFee(fees.getExamFee());
            }

            if(fees.getTuitionFee() != 0 && fees.getTuitionFee() != uFees.getExamFee()) {
                uFees.setTuitionFee(fees.getTuitionFee());
            }

            if(fees.getUniversityFee() != 0 && fees.getUniversityFee() != uFees.getUniversityFee()) {
                uFees.setUniversityFee(fees.getUniversityFee());
            }

            try {
                feesRepository.save(uFees);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                throw new DataBaseException("Database Error");
            }

        } else {
            throw new NotFoundException("Degree not found");
        }

        Fees rfee;

        try {
            rfee = feesRepository.getById(degreeId);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        return mapper.updateFeesResponse(rfee);
    }
}

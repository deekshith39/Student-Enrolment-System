package com.example.StudentEnrolmentSystem.service.Impl;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.errorHandling.NotFoundException;
import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.*;
import com.example.StudentEnrolmentSystem.model.request.StudentRequest;
import com.example.StudentEnrolmentSystem.model.response.AddStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import com.example.StudentEnrolmentSystem.repository.CourseRepository;
import com.example.StudentEnrolmentSystem.repository.DegreeRepository;
import com.example.StudentEnrolmentSystem.repository.FeesRepository;
import com.example.StudentEnrolmentSystem.repository.StudentRepository;
import com.example.StudentEnrolmentSystem.service.StudentService;
import com.example.StudentEnrolmentSystem.util.Boards;
import com.example.StudentEnrolmentSystem.util.ErrorConstants;
import com.example.StudentEnrolmentSystem.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    FeesRepository feesRepository;

    @Autowired
    DegreeRepository degreeRepository;

    @Autowired
    Mapper mapper;

    /**
     * This method will perform all the required validations and throws exceptions if any, and
     * stored the valid student object into the database.
     * It returns USN(University Serial Number that is generated based on the course and degree) along with the fees.
     *
     * @param student
     * @return usn with fees
     * @throws ValidationUnsuccessfulException
     */
    @Override
    public AddStudentResponse registerStudent(StudentRequest student) throws ValidationUnsuccessfulException, DataBaseException {

        if(student.getName() == null || student.getName() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.NAME_REQUIRED);
        }

        if(student.getDob() == null || student.getDob() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.DOB_REQUIRED);
        }

        String date_regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(date_regex);

        if(!pattern.matcher(student.getDob()).matches()) {
            throw new ValidationUnsuccessfulException(ErrorConstants.DOB_INVALID);
        }

        if(student.getEmail() == "" || student.getEmail() == null) {
            throw new ValidationUnsuccessfulException(ErrorConstants.EMAIL_REQUIRED);
        }

        String email_regex = "^(.+)@(.+)$";
        Pattern email_pattern = Pattern.compile(email_regex);

        if(!email_pattern.matcher(student.getEmail()).matches()) {
            throw new ValidationUnsuccessfulException(ErrorConstants.EMAIL_INVALID);
        }

        if(student.getGender() == "" || student.getGender() == null) {
            throw new ValidationUnsuccessfulException(ErrorConstants.GENDER_REQUIRED);
        }

        if(student.getContactNumber() == null || student.getContactNumber() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.PHONE_REQUIRED);
        }

        Pattern contact_pattern = Pattern.compile("^\\d{10}$");
        Matcher student_contact_matcher = contact_pattern.matcher(student.getContactNumber());

        if(!student_contact_matcher.matches()) {
            throw new ValidationUnsuccessfulException(ErrorConstants.CONTACT_INVALID);
        }

        if(student.getAddress() == "" || student.getAddress() == null) {
            throw new ValidationUnsuccessfulException(ErrorConstants.ADDRESS_REQUIRED);
        }

        if(student.getClassTenBoard() == "" || student.getClassTenBoard() == null) {
            throw new ValidationUnsuccessfulException(ErrorConstants.TEN_BOARD_REQUIRED);
        }

        String tenBoard = student.getClassTenBoard();

        if(!(tenBoard.equals(Boards.STATE.toString()) || tenBoard.equals(Boards.CBSE.toString()) || tenBoard.equals(Boards.ICSE.toString()))) {
            throw new ValidationUnsuccessfulException(ErrorConstants.INVALID_BOARD);
        }

        if(student.getClassTwelveBoard() == "" || student.getClassTwelveBoard() == null) {
            throw new ValidationUnsuccessfulException(ErrorConstants.TWELVE_BOARD_REQUIRED);
        }

        String tweBoard = student.getClassTwelveBoard();

        if(!(tweBoard.equals(Boards.STATE.toString()) || tweBoard.equals(Boards.CBSE.toString()) || tweBoard.equals(Boards.ICSE.toString()))) {
            throw new ValidationUnsuccessfulException(ErrorConstants.INVALID_BOARD);
        }

        if(student.getFatherName() == null || student.getFatherName() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.FATHER_NAME_REQUIRED);
        }

        if(student.getFatherContactNumber() == null) {
            throw new ValidationUnsuccessfulException(ErrorConstants.FATHER_PHONE_REQUIRED);
        }

        Matcher father_contact_matcher = contact_pattern.matcher(student.getFatherContactNumber());

        if(!father_contact_matcher.matches()) {
            throw new ValidationUnsuccessfulException(ErrorConstants.CONTACT_INVALID);
        }

        if(student.getMotherName() == null || student.getMotherName() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.MOTHER_NAME_REQUIRED);
        }

        if(student.getCourseId() == null || student.getCourseId() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.COURSEID_REQUIRED);
        }

        if(student.getDegreeId() == null || student.getDegreeId() == "") {
            throw new ValidationUnsuccessfulException(ErrorConstants.DEGREEID_REQUIRED);
        }

        List<String> Courses;

        try {
            Courses = courseRepository.fetchCourses();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        if(!Courses.contains(student.getCourseId())) {
            throw new ValidationUnsuccessfulException(ErrorConstants.INVALID_COURSEID);
        }

        List<String> Degrees;

        try {
            Degrees = degreeRepository.fetchDegrees();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        if(!Degrees.contains(student.getDegreeId())) {
            throw new ValidationUnsuccessfulException(ErrorConstants.INVALID_DEGREEID);
        }

        if(student.getPassword() == null || student.getPassword().length() < 6) {
            throw new ValidationUnsuccessfulException(ErrorConstants.INVALID_PASSWORD);
        }

        Student saveStudent = mapper.addStudentMapper(student);

        try {
            studentRepository.save(saveStudent);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        String usn = saveStudent.getUserId();

        log.info("Generated USN: " + usn);

        Fees fees;

        try {
            fees = feesRepository.findById(student.getDegreeId()).get();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        float tuitionFee = fees.getTuitionFee();
        float examFee = fees.getExamFee();
        float universityFee = fees.getUniversityFee();
        float totalFees = tuitionFee + examFee + universityFee;

        return new AddStudentResponse(usn,
                tuitionFee,
                universityFee,
                examFee,
                totalFees);
    }

    /**
     * This method returns the authenticated student details from the database.
     *
     * @return student object
     */
    @Override
    public DisplayStudentResponse displayDetails() throws NotFoundException {

        UserDetails userDetails;

        try {
            userDetails =
                    (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new NotFoundException("User not found");
        }

        String usn = userDetails.getUsername();

        Student student;

        if(studentRepository.existsById(usn)) {
            student = studentRepository.findById(usn).get();
        } else {
            throw new NotFoundException("User not found");
        }

        return mapper.displayStudentResponse(student);
    }

}

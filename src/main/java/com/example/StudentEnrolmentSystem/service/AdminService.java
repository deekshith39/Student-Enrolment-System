package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.errorHandling.NotFoundException;
import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.dto.CourseDTO;
import com.example.StudentEnrolmentSystem.model.dto.FeesDTO;
import com.example.StudentEnrolmentSystem.model.response.AddCourseResponse;
import com.example.StudentEnrolmentSystem.model.response.DeleteStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;

public interface AdminService {
    public DisplayStudentResponse fetchStudentById(String usn) throws NotFoundException, DataBaseException;

    public DeleteStudentResponse deleteStudentById(String usn) throws NotFoundException, DataBaseException;

    public AddCourseResponse addCourse(CourseDTO course) throws ValidationUnsuccessfulException, DataBaseException;

    public FeesDTO updateFees(String degreeId, FeesDTO fees) throws NotFoundException, DataBaseException;
}

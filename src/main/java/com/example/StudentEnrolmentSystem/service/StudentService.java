package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.errorHandling.NotFoundException;
import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.request.StudentRequest;
import com.example.StudentEnrolmentSystem.model.response.AddStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;

public interface StudentService {

    /**
     * This method will perform all the required validations and throws exceptions if any, and
     * stored the valid student object into the database.
     * It returns USN(University Serial Number that is generated based on the course and degree) along with the fees.
     *
     * @param student
     * @return usn with fees
     * @throws ValidationUnsuccessfulException
     */
    public AddStudentResponse registerStudent(StudentRequest student) throws ValidationUnsuccessfulException, DataBaseException;

    /**
     * The Get mapping will display the authenticated student details.
     *
     * @return student object
     */
    public DisplayStudentResponse displayDetails() throws NotFoundException;
}

package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.errorHandling.NotFoundException;
import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.request.StudentRequest;
import com.example.StudentEnrolmentSystem.model.response.AddStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import com.example.StudentEnrolmentSystem.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    @ApiOperation(value = "Student Welcome Page")
    public String studentWelcomePage() {
        return "Welcome Student";
    }

    /**
     * This api is to register a valid student.
     * This is permitted to all.
     *
     * @param student
     * @return usn with fees
     * @throws ValidationUnsuccessfulException
     */
    @PostMapping("/add_student")
    @ApiOperation(value = "Register a new student",
    notes = "Provide the student details to be inserted into the database",
    response = AddStudentResponse.class)
    public AddStudentResponse registerStudent(@ApiParam(value = "Complete details of the student", required = true)
                                                  @RequestBody StudentRequest student) throws ValidationUnsuccessfulException, DataBaseException {
        return studentService.registerStudent(student);
    }

    /**
     * The api will return the authenticated student details.
     * This is permitted to student.
     *
     * @return student object
     */
    @GetMapping("/display_details")
    @ApiOperation(value = "Display student details",
    notes = "Displays the details of the authenticated student",
    response = DisplayStudentResponse.class)
    public DisplayStudentResponse displayDetails() throws NotFoundException {
        return studentService.displayDetails();
    }

}

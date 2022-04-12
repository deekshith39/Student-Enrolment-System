package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.errorHandling.NotFoundException;
import com.example.StudentEnrolmentSystem.errorHandling.ValidationUnsuccessfulException;
import com.example.StudentEnrolmentSystem.model.dto.CourseDTO;
import com.example.StudentEnrolmentSystem.model.dto.FeesDTO;
import com.example.StudentEnrolmentSystem.model.response.AddCourseResponse;
import com.example.StudentEnrolmentSystem.model.response.DeleteStudentResponse;
import com.example.StudentEnrolmentSystem.model.response.DisplayStudentResponse;
import com.example.StudentEnrolmentSystem.service.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    @ApiOperation(value = "Admin Welcome Page")
    public String AdminWelcomePage() {
        return "Welcome Admin";
    }

    /**
     * This API is used to search student by USN
     *
     * @param usn
     * @return student object
     * @throws NotFoundException
     */
    @GetMapping("/search_student/{usn}")
    @ApiOperation(value = "Finds Students by id",
    notes = "Provide the usn to look up specific student from the student table",
    response = DisplayStudentResponse.class)
    public DisplayStudentResponse searchStudent(@ApiParam(value = "usn/Id for the student you need to retrieve", required = true)
                                                    @PathVariable String usn) throws NotFoundException, DataBaseException {
        return adminService.fetchStudentById(usn);
    }

    /**
     * This API deletes the student based on the USN
     *
     * @param usn
     * @return DeleteStudentResponse
     * @throws NotFoundException
     */
    @DeleteMapping("/delete_student/{usn}")
    @ApiOperation(value = "Delete the student by Id",
    notes = "Delete here basically sets the status of the student as inactive",
    response = DeleteStudentResponse.class)
    public DeleteStudentResponse deleteStudent(@ApiParam(value = "usn/id to set as inactive in the user table", required = true)
                                                   @PathVariable String usn) throws NotFoundException, DataBaseException {
        return adminService.deleteStudentById(usn);
    }

    /**
     * This API helps in adding a new course into the database
     *
     * @param course
     * @return AddCourseResponse
     * @throws ValidationUnsuccessfulException
     * @throws DataBaseException
     */
    @PostMapping("/add_course")
    @ApiOperation(value = "Add the course object",
    notes = "Provide a course object to insert in to the database",
    response = AddCourseResponse.class)
    public AddCourseResponse addCourse(@ApiParam(value = "Provide the course object that has to be inserted into the course table", required = true)
                                           @RequestBody CourseDTO course) throws ValidationUnsuccessfulException, DataBaseException {
        return adminService.addCourse(course);
    }

    /**
     * This API helps in updating the fees
     *
     * @param degreeId
     * @param fees
     * @return Updated degree object
     * @throws NotFoundException
     * @throws DataBaseException
     */
    @PutMapping("/update_fees/{degreeId}")
    @ApiOperation(value = "Update Fees by DegreeId",
    notes = "Provide the degreeId and Fees object to update the fees based on the degreeId",
    response = FeesDTO.class)
    public FeesDTO updateFees(@ApiParam(value = "degreeId for which the fees has to be updated", required = true)
                               @PathVariable String degreeId, @RequestBody FeesDTO fees) throws NotFoundException, DataBaseException {
        return adminService.updateFees(degreeId, fees);
    }
}

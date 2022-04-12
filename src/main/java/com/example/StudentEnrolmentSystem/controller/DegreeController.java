package com.example.StudentEnrolmentSystem.controller;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.response.DisplayDegreeResponse;
import com.example.StudentEnrolmentSystem.service.DegreeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/degree")
public class DegreeController {

    @Autowired
    private DegreeService degreeService;

    /**
     * This API will fetch all the degrees offered by the college.
     *
     * @return Degrees offered
     * @throws DataBaseException
     */
    @GetMapping("/display_degrees")
    @ApiOperation(value = "Find all degrees",
    notes = "Provides all the degrees offered by the college",
    response = DisplayDegreeResponse.class)
    public DisplayDegreeResponse displayDegrees() throws DataBaseException {
        return degreeService.displayDegrees();
    }

}

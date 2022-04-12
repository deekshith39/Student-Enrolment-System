package com.example.StudentEnrolmentSystem.model.response;

import com.example.StudentEnrolmentSystem.model.Degree;
import lombok.Data;

import java.util.List;

@Data
public class DisplayDegreeResponse {
    private String college;
    private List<Degree> degreesOffered;

    public DisplayDegreeResponse() {
        this.college = "BMS College of Engineering";
    }
}

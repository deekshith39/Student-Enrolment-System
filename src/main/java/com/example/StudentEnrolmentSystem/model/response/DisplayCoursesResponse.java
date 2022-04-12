package com.example.StudentEnrolmentSystem.model.response;

import com.example.StudentEnrolmentSystem.model.Course;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DisplayCoursesResponse {
    private String college;
    private List<Course> coursesOffered;

    public DisplayCoursesResponse() {
        this.college = "BMS College of Engineering";
    }
}

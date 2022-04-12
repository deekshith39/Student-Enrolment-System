package com.example.StudentEnrolmentSystem.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {

    @ApiModelProperty(value = "Course Code", example = "BMSCS", required = true)
    private String courseId;

    @ApiModelProperty(value = "Course Name", example = "Computer Science", required = true)
    private String courseName;

}

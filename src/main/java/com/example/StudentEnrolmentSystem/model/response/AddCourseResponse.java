package com.example.StudentEnrolmentSystem.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseResponse {
    private Timestamp timestamp;
    private String courseCode;
    private String message;
}

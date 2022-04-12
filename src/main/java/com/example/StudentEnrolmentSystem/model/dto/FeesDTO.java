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
public class FeesDTO {

    @ApiModelProperty(value = "Degree Id", example = "BMSBE")
    private String degreeId;

    @ApiModelProperty(value = "Tuition Fees")
    private float tuitionFee;

    @ApiModelProperty(value = "Exam Fees")
    private float examFee;

    @ApiModelProperty(value = "University Fees")
    private float universityFee;
}

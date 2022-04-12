package com.example.StudentEnrolmentSystem.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {

    @ApiModelProperty(value = "Password", example = "example@222", required = true)
    private String password;

    @ApiModelProperty(value = "Student Name", example = "Ravi Kumar")
    private String name;

    @ApiModelProperty(value = "Date of Birth", example ="12/12/12", required = true)
    private String dob;

    @ApiModelProperty(value = "E-mail", example = "example@gmail.com", required = true)
    private String email;

    @ApiModelProperty(value = "Gender", example = "male", required = true)
    private String gender;

    @ApiModelProperty(value = "Contact Number", example = "9023123412", required = true)
    private String contactNumber;

    @ApiModelProperty(value = "Address", example = "Bangalore", required = true)
    private String address;

    @ApiModelProperty(value = "Tenth Class Board", example = "CBSE", required = true)
    private String classTenBoard;

    @ApiModelProperty(value = "Tenth Class Percentage", example = "91.1", required = true)
    private float classTenPercentage;

    @ApiModelProperty(value = "Twelfth Class Board ", required = true)
    private String classTwelveBoard;

    @ApiModelProperty(value = "Class Twelfth Percentage", example = "95.6", required = true)
    private float classTwelvePercentage;

    @ApiModelProperty(value = "Father's Name", required = true)
    private String fatherName;

    @ApiModelProperty(value = "Father's Phone Number", example = "9023123412", required = true)
    private String fatherContactNumber;

    @ApiModelProperty(value = "Mother's Name", required = true)
    private String motherName;

    @ApiModelProperty(value = "Course Code", required = true, example = "BMSCS")
    private String courseId;

    @ApiModelProperty(value = "Degree Code", required = true, example = "BMSBE")
    private String degreeId;

}

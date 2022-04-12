package com.example.StudentEnrolmentSystem.model.response;

import com.example.StudentEnrolmentSystem.model.Course;
import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisplayStudentResponse {

    private String userId;

    private Course course;

    private Degree degree;

    private String name;

    private String dob;

    private String email;

    private String gender;

    private String contactNumber;

    private String address;

    private String classTenBoard;

    private float classTenPercentage;

    private String classTwelveBoard;

    private float classTwelvePercentage;

    private String fatherName;

    private String fatherContactNumber;

    private String motherName;

}

package com.example.StudentEnrolmentSystem.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStudentResponse {
    private String usn;
    private float tuitionFees;
    private float universityFees;
    private float examFees;
    private float totalFees;
}

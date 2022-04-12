package com.example.StudentEnrolmentSystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteStudentResponse {
    private Timestamp timestamp;
    private String usn;
    private String message;
}

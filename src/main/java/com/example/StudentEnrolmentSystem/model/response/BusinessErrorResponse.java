package com.example.StudentEnrolmentSystem.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessErrorResponse {
    private Timestamp timestamp;
    private String code;
    private String message;
}

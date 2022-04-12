package com.example.StudentEnrolmentSystem.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationUnsuccessfulException extends Exception {
    private String code;
//    private String message;
}

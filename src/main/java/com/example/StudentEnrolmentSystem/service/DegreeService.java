package com.example.StudentEnrolmentSystem.service;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.response.DisplayDegreeResponse;

public interface DegreeService {
   public DisplayDegreeResponse displayDegrees() throws DataBaseException;
}

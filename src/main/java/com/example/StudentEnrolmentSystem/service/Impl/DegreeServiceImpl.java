package com.example.StudentEnrolmentSystem.service.Impl;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.model.Degree;
import com.example.StudentEnrolmentSystem.model.response.DisplayDegreeResponse;
import com.example.StudentEnrolmentSystem.repository.DegreeRepository;
import com.example.StudentEnrolmentSystem.service.DegreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DegreeServiceImpl implements DegreeService {

    @Autowired
    DegreeRepository degreeRepository;

    @Override
    public DisplayDegreeResponse displayDegrees() throws DataBaseException {
        List<Degree> degrees;

        try {
            degrees = degreeRepository.findAll();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DataBaseException("Database Error");
        }

        DisplayDegreeResponse degreeResponse = new DisplayDegreeResponse();
        degreeResponse.setDegreesOffered(degrees);

        return degreeResponse;
    }
}

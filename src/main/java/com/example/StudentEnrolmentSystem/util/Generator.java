package com.example.StudentEnrolmentSystem.util;

import com.example.StudentEnrolmentSystem.errorHandling.DataBaseException;
import com.example.StudentEnrolmentSystem.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Generator {

    @Autowired
    StudentRepository studentRepository;

    public String generateUsn(String courseId, String degreeId) throws DataBaseException {
        String usn = "BMS" + degreeId.substring(3) + courseId.substring(3);
        int rollno;

        try {
            rollno = studentRepository.countByCourse(courseId);
        } catch (AopInvocationException e) {
            rollno = 0;
        } catch (NullPointerException e) {
            rollno = 0;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new DataBaseException(e.getMessage());
        }

        rollno++;

        if(rollno <= 9) {
            usn += "00";
        } else if(rollno <= 99) {
            usn += "0";
        } else {
            usn += "";
        }

        usn += Integer.toString(rollno);

        return usn;
    }

}

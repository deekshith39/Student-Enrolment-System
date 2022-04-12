package com.example.StudentEnrolmentSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Degree {

    @Id
    private String degreeId;

    @Column(nullable = false)
    private String degreeName;

    @Column(nullable = false)
    private int duration;
}

package com.example.StudentEnrolmentSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    @Id
    @Column(name = "usn")
    private String userId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usn", referencedColumnName = "userId")
    @MapsId
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "courseId",
            referencedColumnName = "courseId"
    )
    private Course course;

    @ManyToOne
    @JoinColumn(
            name = "degreeId",
            referencedColumnName = "degreeId"
    )
    private Degree degree;

    @Column(nullable = false)
    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @Column(nullable = false)
    private String dob;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String classTenBoard;

    @Column(nullable = false)
    private float classTenPercentage;

    @Column(nullable = false)
    private String classTwelveBoard;

    @Column(nullable = false)
    private float classTwelvePercentage;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String fatherContactNumber;

    @Column(nullable = false)
    private String motherName;

}


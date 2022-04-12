package com.example.StudentEnrolmentSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fees {

    @Id
    @Column(name = "DId")
    private String degreeId;

    @JsonIgnore
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "DId", referencedColumnName = "degreeId")
    @MapsId
    private Degree degree;

    @Column(nullable = false)
    private float tuitionFee;

    @Column(nullable = false)
    private float examFee;

    @Column(nullable = false)
    private float universityFee;

}

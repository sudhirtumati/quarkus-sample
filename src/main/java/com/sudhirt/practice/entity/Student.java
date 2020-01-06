package com.sudhirt.practice.entity;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Student
 */
@Entity
@Data
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    @JsonbDateFormat(value = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
}
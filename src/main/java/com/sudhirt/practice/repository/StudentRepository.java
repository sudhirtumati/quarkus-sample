package com.sudhirt.practice.repository;

import javax.enterprise.context.ApplicationScoped;

import com.sudhirt.practice.entity.Student;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

/**
 * StudentRepository
 */
@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student, String> {

    
}
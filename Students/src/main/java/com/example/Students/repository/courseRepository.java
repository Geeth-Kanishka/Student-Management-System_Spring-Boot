package com.example.Students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Students.model.Course;

@Repository
public interface courseRepository  extends JpaRepository<Course, Long>{

}

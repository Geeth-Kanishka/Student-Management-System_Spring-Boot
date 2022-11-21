package com.example.Students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Students.model.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}

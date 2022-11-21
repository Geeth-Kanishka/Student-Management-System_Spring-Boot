package com.example.Students.service;

import java.util.List;
import java.util.Optional;

import com.example.Students.model.Student;


public interface StudentService {
	
	Student saveStudent(Student student);
	List<Student> getAllStudents();
	Student getStudentById(long id);
	Student updateStudent(Student student,long id);
	Student getOne(long studentId);
	Optional<Student> findById(Long id);
	
	void deleteStudent(long id);

}

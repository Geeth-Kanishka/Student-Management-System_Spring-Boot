package com.example.Students.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Students.model.Student;
import com.example.Students.repository.StudentRepository;
import com.example.Students.service.StudentService;
import com.example.Students.exception.ResourceNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	
	 @Override
	    public Optional<Student> findById(Long id) {
	        return studentRepository.findById(id);
	    }

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		return student;
	}

	@Override
	public Student updateStudent(Student student, long id) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		existingStudent.setName(student.getName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setAge(student.getAge());
		existingStudent.setScore(student.getScore());

		studentRepository.save(existingStudent);
		return existingStudent;
	}

	@Override
	public void deleteStudent(long id) {
		studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
		studentRepository.deleteById(id);
	}

	@Override
	public Student getOne(long studentId) {
		// TODO Auto-generated method stub
		return studentRepository.findById(studentId).get();
	}

}

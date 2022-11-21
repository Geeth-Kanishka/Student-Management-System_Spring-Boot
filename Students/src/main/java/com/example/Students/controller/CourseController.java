package com.example.Students.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Students.model.Course;
import com.example.Students.model.Lecturer;
import com.example.Students.model.Student;
import com.example.Students.service.CourseService;
import com.example.Students.service.LecturerService;
import com.example.Students.service.StudentService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private CourseService courseservice;
	private StudentService studentservice;
	private LecturerService lecturerService;

	public CourseController(CourseService courseservice, StudentService studentservice,  LecturerService lecturerService) {
		super();
		this.courseservice = courseservice;
		this.studentservice = studentservice;
		this.lecturerService = lecturerService;
	}

	@PostMapping()
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {

		return new ResponseEntity<Course>(courseservice.saveCourse(course), HttpStatus.CREATED);

	}

	@GetMapping
	public List<Course> getAllCourse() {
		return courseservice.getAllCourses();

	}

	@PutMapping("/{courseId}/students/{studentId}")
	Course enrollStudents(
			@PathVariable Long courseId,
			@PathVariable Long studentId
			
			) {
		
		Course course = courseservice.getOne(courseId);
		Student student = studentservice.getOne(studentId);
		course.enrollStudent(student);
		return courseservice.saveCourse(course);
	}
	
	@PutMapping("/{courseId}/lecturers/{lecturerId}")
	Course assignLecturerToCourse(
			@PathVariable Long courseId,
			@PathVariable Long lecturerId
			
			) {
		
		Course course = courseservice.getOne(courseId);
		Lecturer lecturer = lecturerService.getOne(lecturerId);
		course.assignLecturer(lecturer);
		return courseservice.saveCourse(course);
	}
	
	@GetMapping("/exception/throw")
	public void getException() throws Exception {
	    throw new Exception("error");
	}
	

}

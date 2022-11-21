package com.example.Students.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Students.exception.BadArgumentsException;
import com.example.Students.exception.InternalException;
import com.example.Students.exception.ResourceNotFoundException;
import com.example.Students.model.Student;
import com.example.Students.service.EmailService;
import com.example.Students.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	private StudentService studentService;
	
	@Autowired
	private EmailService emailservice;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@PostMapping("/saveStudent")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		String email=student.getEmail();
		String name= student.getName();
		int age=student.getAge();
		String score= student.getScore();
		emailservice.sendSimpleMessage(email, "Student registration", 
				"Hi "+name+ System.lineSeparator()
				+"\nYou have being successfully added to the system"
				+"\nName: "+name
				+"\nAge: "+age
				+"\nScore: "+score+"\n"
				+"\nThank you");
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);

	}
	
	@GetMapping("/getAllStudents")
	public ResponseEntity<Object> getAllBooks() {
		return new ResponseEntity<Object>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/getStudents")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();

	}
	@GetMapping("/getid/{id}")
	public Optional<Student> getid(@PathVariable("id") long studentid) {
		return studentService.findById(studentid);

	}

	@GetMapping("{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentid) {

		return new ResponseEntity<Student>(studentService.getStudentById(studentid), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {

		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);

	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Student> updateStudent2(@PathVariable("id") long id, @RequestBody Student student) {
		 Optional<Student> existingStudent = studentService.findById(id);
		 student.setId(id);
		 student = studentService.saveStudent(student);

		 return ResponseEntity
                 .ok()
                 .body(student);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
		Student student =studentService.getOne(id);
		String email=student.getEmail();
		String name= student.getName();

		emailservice.sendSimpleMessage(email, "Student Deleted", 
				"Hi "+name+ System.lineSeparator()
				+"\nYou have being successfully removed from the system"
				+"\nName: "+name+"\n"
				+"\nThank you");
		
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("deleted successfully ", HttpStatus.OK);

	}
	
	@GetMapping("/exception/throw")
	public void getException() throws Exception {
	    throw new Exception("error");
	}
	
	@GetMapping("/exception/{exception_id}")
	public void getSpecificException(@PathVariable("exception_id") String pException) {
	    if("not_found".equals(pException)) {
	        throw new ResourceNotFoundException("resource not found");
	    }
	    else if("bad_arguments".equals(pException)) {
	        throw new BadArgumentsException("bad arguments");
	    }
	    else {
	        throw new InternalException("internal error");
	    }
	}
	
	

}

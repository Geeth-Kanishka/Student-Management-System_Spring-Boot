package com.example.Students.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Students.model.Lecturer;
import com.example.Students.service.LecturerService;

@RestController
@RequestMapping("/lecturers")
public class LecturerController {

	private LecturerService lecturerService;

	public LecturerController(LecturerService lecturerService) {
		super();
		this.lecturerService = lecturerService;
	}

	@PostMapping()
	public ResponseEntity<Lecturer> saveLecturer(@RequestBody Lecturer lecturer) {

		return new ResponseEntity<Lecturer>(lecturerService.saveLecturer(lecturer), HttpStatus.CREATED);

	}

	@GetMapping
	public List<Lecturer> getAllLecturers() {
		return lecturerService.getAllLecturers();

	}
	
	@GetMapping("/exception/throw")
	public void getException() throws Exception {
	    throw new Exception("error");
	}

}

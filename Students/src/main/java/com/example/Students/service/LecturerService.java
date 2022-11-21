package com.example.Students.service;

import java.util.List;

import com.example.Students.model.Lecturer;

public interface LecturerService {
	Lecturer saveLecturer(Lecturer lecturer);

	List<Lecturer> getAllLecturers();

	Lecturer getOne(long lecturerId);

}

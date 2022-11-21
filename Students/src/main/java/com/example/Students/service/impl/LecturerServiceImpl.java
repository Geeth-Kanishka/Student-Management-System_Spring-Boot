package com.example.Students.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Students.model.Lecturer;
import com.example.Students.repository.LecturerRepository;
import com.example.Students.service.LecturerService;

@Service
public class LecturerServiceImpl implements LecturerService {

	private LecturerRepository lecturerRepository;

	public LecturerServiceImpl(LecturerRepository lecturerRepository) {
		super();
		this.lecturerRepository = lecturerRepository;
	}

	@Override
	public Lecturer saveLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		return lecturerRepository.save(lecturer);
	}

	@Override
	public List<Lecturer> getAllLecturers() {
		// TODO Auto-generated method stub
		return lecturerRepository.findAll();
	}

	@Override
	public Lecturer getOne(long lecturerId) {
		// TODO Auto-generated method stub
		return lecturerRepository.findById(lecturerId).get();
	}

}

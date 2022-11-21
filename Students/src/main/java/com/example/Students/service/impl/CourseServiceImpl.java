package com.example.Students.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Students.service.CourseService;
import com.example.Students.model.Course;
import com.example.Students.repository.courseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private courseRepository courseRepository;

	public CourseServiceImpl(com.example.Students.repository.courseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public Course getOne(long courseId) {
		// TODO Auto-generated method stub
		return courseRepository.findById(courseId).get();
	}


	

}

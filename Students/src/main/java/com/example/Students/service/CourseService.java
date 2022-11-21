package com.example.Students.service;

import java.util.List;

import com.example.Students.model.Course;


public interface CourseService {
	
	Course saveCourse(Course course);
	List<Course> getAllCourses();
	Course getOne(long courseId);

}

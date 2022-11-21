package com.example.Students.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "students_enrolled", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = {
			@JoinColumn(name = "student_id") })
	private Set<Student> enrolled = new HashSet<>();
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="lecturer_id", referencedColumnName="id")
	private Lecturer lecturer;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getEnrolled() {
		return enrolled;
	}

	public void enrollStudent(Student student) {
		enrolled.add(student);
		// TODO Auto-generated method stub
		
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void assignLecturer(Lecturer lecturer2) {
		this.lecturer=lecturer2;
		
	}

}

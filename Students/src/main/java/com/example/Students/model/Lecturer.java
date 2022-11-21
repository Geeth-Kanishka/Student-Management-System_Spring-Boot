package com.example.Students.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lecturers")
public class Lecturer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy= "lecturer")
	private Set<Course> courses= new HashSet<>();

	public Set<Course> getCourses() {
		return courses;
	}

	public Lecturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecturer(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package com.example.Students.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "students")
public class Student {

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id,String name, String email, String score, int age) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;
		this.score = score;
		this.age = age;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	private String email;
	private String score;
	private int age;

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY, cascade = { CascadeType.ALL },mappedBy = "enrolled")
	Set<Course> courses = new HashSet<>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Course> getCourses() {
		return courses;
	}

}

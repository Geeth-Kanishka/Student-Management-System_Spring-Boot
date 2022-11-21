package com.example.Students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Students.model.Course;
import com.example.Students.model.Lecturer;
import com.example.Students.model.Student;
import com.example.Students.service.CourseService;
import com.example.Students.service.LecturerService;
import com.example.Students.service.StudentService;
import com.example.Students.service.EmailService;

@Controller
public class ViewController {
	@Autowired
	private EmailService emailservice;

	@Autowired
	private StudentService studentService;

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private CourseService courseservice;

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/lecturer")
	public String lecturer(Model model) {
		List<Lecturer> listlecturers = lecturerService.getAllLecturers();
		model.addAttribute("listlecturers", listlecturers);
		return "lecturer";
	}

	@GetMapping("/course")
	public String course(Model model) {
		List<Course> listcourses = courseservice.getAllCourses();
		model.addAttribute("listcourses", listcourses);
		return "course";
	}

	@GetMapping("/new")
	public String viewnew(Model model) {
		List<Student> liststudent = studentService.getAllStudents();
		model.addAttribute("liststudent", liststudent);
		return "new";
	}

	@RequestMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(name = "id") int id) {
		Student student =studentService.getOne(id);
		String email=student.getEmail();
		String name= student.getName();

		emailservice.sendSimpleMessage(email, "Student Deleted", 
				"Hi "+name+ System.lineSeparator()
				+"\nYou have being successfully removed from the system"
				+"\nName: "+name+"\n"
				+"\nThank you");
		studentService.deleteStudent(id);
		return "redirect:/new";
	}
	
	@RequestMapping("/assign/{id}")
	public String assign(@PathVariable(name = "id") int id,@RequestParam(value = "participant", required = false) long participant) {

		Course course = courseservice.getOne(id);
		Lecturer lecturer = lecturerService.getOne(participant);
		course.assignLecturer(lecturer);
	    courseservice.saveCourse(course);
		return "redirect:/course";
	}
	
	
	@RequestMapping("/enrollnew/{id}")
	public String enrollnew(@PathVariable(name = "id") int id,@RequestParam(value = "studentid", required = false) long studentid) {
		Course course = courseservice.getOne(id);
		Student student = studentService.getOne(studentid);
		course.enrollStudent(student);
		courseservice.saveCourse(course);
		return "redirect:/course";
	}

	@ModelAttribute("lecturer")
	public Lecturer getCustomerObject() {
		return new Lecturer();
	}

	@RequestMapping(value = "/saveLecturer", method = RequestMethod.POST)
	public String saveLecturer(@ModelAttribute("lecturer") Lecturer lecturer) {
		lecturerService.saveLecturer(lecturer);
		return "redirect:/lecturer";
	}
	
	@ModelAttribute("course")
	public Course getCustomerObject2() {
		return new Course();
	}

	@RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseservice.saveCourse(course);
		return "redirect:/course";
	}
	
	@GetMapping("/exception/throw")
	public void getException() throws Exception {
	    throw new Exception("error");
	}

}

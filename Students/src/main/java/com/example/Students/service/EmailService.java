package com.example.Students.service;

public interface EmailService {
	void sendSimpleMessage(
		      String to, String subject, String text);
}

package com.mastek.jobsapp.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mastek.jobsapp.entities.User;

@Service
public class EmailService {
//	mail sender
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void welcomeEmail(User user) throws MailException {
		//send email here 
		SimpleMailMessage mail = new SimpleMailMessage(); 
		mail.setTo(user.getEmail());
		mail.setFrom("it.jobswatch11@gmail.com");
		mail.setSubject("IT Jobs Watch - Registration");
		mail.setText("Hi " + user.getUsername() + "," + "\n" + "Thank you for registering an account with IT Jobs Watch." + "\n" + 
		"Your unique ID number is: " + user.getUserId());
		
		javaMailSender.send(mail);
	}
}

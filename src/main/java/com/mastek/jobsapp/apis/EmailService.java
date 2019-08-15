package com.mastek.jobsapp.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mastek.jobsapp.entities.Company;
import com.mastek.jobsapp.entities.User;

@Service
public class EmailService {
	//mail sender
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	// send an email when a new user is registered, throw mail exception to change errors and pass in the new user
	public void userWelcomeEmail(User user) throws MailException {
		// create a new simple mail message 
		SimpleMailMessage mail = new SimpleMailMessage(); 
		// get email from user, to send email to 
		mail.setTo(user.getEmail());
		mail.setFrom("it.jobswatch11@gmail.com");
		mail.setSubject("Tech Jobs - Registration");
		// email body
		mail.setText("Hi " + user.getUsername() + "," + "\n\n" + "Thank you for registering an account with Tech Jobs." + "\n" + 
		"Your unique ID number is: " + user.getUserId());
		// send the email
		javaMailSender.send(mail);
	}
	
	// works the same as userWelcomeEmail but is instead for company
	public void companyWelcomeEmail(Company company) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(company.getEmail());
		mail.setFrom("it.jobswatch11@gmail.com");
		mail.setSubject("Tech Jobs - Registration");
		mail.setText("Hi " + company.getCompanyName() + ", " + "\n\n" + "Thank you for registering an account with Tech Jobs" + "\n\n" + 
		"Please Ensure All The Details Below Are Correct" + "\n\n" + "Username: " + company.getUsername() + "\n" + "ID Number: " + company.getCompanyId() + 
		"\n" + "Location: " + company.getHqLocation() + "\n" + "Linked In: " + company.getLinkedIn() + "\n\n" + "If any of these details are incorrect please "
		+ "log in and update them" + "\n\n" + "Thanks for using the IT Jobs Market!");
		
		javaMailSender.send(mail);
	}
	
	// send an email to a user when there details are updated
	public void userChangeDetailsEmail(User user) throws MailException {
		// create a new simple mail message 
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("it.jobswatch11@gmail.com");
		mail.setSubject("Tech Jobs - Profile Updates");
		mail.setText("Hi " + user.getUsername() + ", " + "\n\n" + "Changes have been made to youd account. If this was you please "
		+ "ignore this message, however if this wasnt you please log in and update your password.");
		
		javaMailSender.send(mail);
	}
	
	// send an email to a company when there details are updated
		public void companyChangeDetailsEmail(Company company) throws MailException {
			// create a new simple mail message 
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(company.getEmail());
			mail.setFrom("it.jobswatch11@gmail.com");
			mail.setSubject("Tech Jobs - Profile Updates");
			mail.setText("Hi " + company.getUsername() + ", " + "\n\n" + "Changes have been made to your account. If this was you please "
			+ "ignore this message, however if this wasnt you please log in and update your password.");
			
			javaMailSender.send(mail);
		}
}

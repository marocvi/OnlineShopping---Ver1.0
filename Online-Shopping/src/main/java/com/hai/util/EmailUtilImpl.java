package com.hai.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

public class EmailUtilImpl implements EmailUtil {
	private Email email;
	private Logger LOGGER;
	private EmailContent emailContent;

	

	public EmailUtilImpl() {
		LOGGER = Logger.getLogger(EmailUtilImpl.class);
		emailContent = new EmailContent();
	}

	public EmailUtilImpl setToEmail(String email)    {
		emailContent.setToEmail(email);
		return this;
	}
	public EmailUtilImpl setFromEmail(String fromEmail) {
		emailContent.setFromEmail(fromEmail);
		return this;
		
	}
	public EmailUtilImpl setPurpose(EmailPurpose purpose) {
		String message = "";
		String subject = "";
		switch(purpose) {
		case VERIFICATION:
			message ="PLease clink the link below to verify your account: \n"
					+ "http://localhost:8080//Online-Shopping/account?action=verify&verifyID=";
			subject = "Verify Your Email";
			break;
		case NOTIFICATION:
			break;
		default:
			break;
		}
		emailContent.setMessage(message);
		emailContent.setSubject(subject);
		
		return this;
	}
	public EmailUtilImpl setVerifyID(String verifyID) {
		emailContent.setVerifyID(verifyID);
		return this;
	}

	public Email send()   {
		//Send Email
		try {
		email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("marocvi89@gmail.com", "1qazsedcvgyhnmju"));
		email.setSSLOnConnect(true);
		email.setFrom(emailContent.getFromEmail(), "Shopping Online");
		email.setSubject(emailContent.getSubject());
		String message ="Hi " + emailContent.getToEmail() +"! \n";
		if(emailContent.getVerifyID()!=null) {
			message += emailContent.getMessage() + emailContent.getVerifyID();
		}
		else
			message += emailContent.getMessage();
		email.setMsg(message);
		email.addTo(emailContent.getToEmail());
		email.send();
		}
		catch(EmailException e) {
			e.printStackTrace();
			LOGGER.error("Can't send email");
		}
		return email;
	};
	
	
	
	///Email Content
	
	class EmailContent{
		String message;
		String fromEmail;
		String toEmail;
		String verifyID;
		String subject;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getFromEmail() {
			return fromEmail;
		}
		public void setFromEmail(String fromEmail) {
			this.fromEmail = fromEmail;
		}
		public String getToEmail() {
			return toEmail;
		}
		public void setToEmail(String toEmail) {
			this.toEmail = toEmail;
		}
		public String getVerifyID() {
			return verifyID;
		}
		public void setVerifyID(String verifyID) {
			this.verifyID = verifyID;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		
	}


	

}

package com.hai.util;

import org.apache.commons.mail.Email;
/**
 * This interface including function for sending email.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface EmailUtil {

	public static enum EmailPurpose {
		VERIFICATION, NOTIFICATION
	}
	/**
	 * Set the recieved email
	 * @param email The recieved Email
	 * @return 
	 */
	public EmailUtil setToEmail(String email);
	
	/**
	 * Set  the system email
	 * @param fromEmail The email user use to send email to User.
	 * @return
	 */
	public EmailUtil setFromEmail(String fromEmail);
	/**
	 * Depending on purpose this fucntion set message and subject for email.
	 * @param purpose The purpose of the email
	 * @return
	 */
	public EmailUtil setPurpose(EmailPurpose purpose);
	/**
	 * If purpose of the email is verification or confirmation, this function is called to verify/confirm users.
	 * @param verifyID The unique ID number send to user's email.
	 * @return
	 */
	public EmailUtil setVerifyID(String verifyID);
	/** 
	 * Execute send email.
	 * @return The email object which is send to users.
	 */
	public Email send();
}

package com.hai.iservice;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.hai.model.Users;

public interface IUserService {
	//Login status
	
	
	
	/**
	 * Check whether user exist in Database
	 * @param user
	 * @return
	 */
	public boolean checkExist(String email) ;
	
	/**
	 * Save user to DB
	 * @param user
	 * @see {@link com.hai.idao.IUserDAO}
	 */
	public void saveUser(Users user);
	
	
	/**
	 * Check and Validation User'Information
	 * @return  number of errors
	 */
	public HashMap<String, String> validateUserInfor(HttpServletRequest request);
	/**
	 *  Change loginstatus to active
	 *  
	 * @param verifyID
	 * @return
	 */
	public boolean verifyUser(String verifyID) ;
	/**
	 * This function for authentication of user's information 
	 * Check whether user verified or not.
	 * Check whether loginStatus is ACTIVE
	 * @param email
	 * @param passwords
	 * @return
	 */
	
	public boolean authenticate(String email, String passwords);
	
	/**
	 * 
	 * @see com.hai.idao.IUserDAO#findByEmail(String)
	 * @param email
	 * @return
	 */
	public Users getUserByEmail(String email);
	
	/**
	 * @see com.hai.idao.IUserDAO#update(Users)
	 * @param user
	 */
	public void updateUser(Users user);
	
	/**
	 * This function take request and get information of user 
	 * @param request  The request com from client
	 * @return The user information from request.
	 */
	public Users getUserFromRequest(HttpServletRequest request);
	
	/**
	 * Validation of information user enter when they want to change password
	 * @return
	 */
	public HashMap<String, String> validateChangePassword(HttpServletRequest request);
	
	
	
	
	
}

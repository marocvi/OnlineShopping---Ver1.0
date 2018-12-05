package com.hai.idao;

import java.util.List;

import com.hai.model.Users;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IUserDAO {
	
	public boolean save(Users user);

	public boolean update(Users user);

	public boolean delete(Users user);

	public boolean delete(Integer userID);

	public Users findById(Integer userID);
	
	public Users findByEmail(String email);

	public List<Users> findAll();
	
	public List<Users> findByProperty(String name, Object proValue);
	

}

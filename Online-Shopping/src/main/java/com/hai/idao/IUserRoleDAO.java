package com.hai.idao;

import java.util.List;

import com.hai.model.UserRole;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IUserRoleDAO {
	
	public boolean save(UserRole userRole);

	public boolean update(UserRole userRole);

	public boolean delete(UserRole userRole);

	public boolean delete(Integer userRoleID);

	public UserRole findById(Integer userRoleID);

	public List<UserRole> findAll();
	
	public List<UserRole> findByProperty(String name, Object proValue);
}

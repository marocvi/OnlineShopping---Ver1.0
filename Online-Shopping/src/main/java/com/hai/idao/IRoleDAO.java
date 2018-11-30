package com.hai.idao;

import java.util.List;

import com.hai.model.Role;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IRoleDAO {
	
	public boolean save(Role role);

	public boolean update(Role role);

	public boolean delete(Role role);

	public boolean delete(Integer roleID);

	public Role findById(Integer roleID);

	public List<Role> findAll();
	
	public List<Role> findByProperty(String name, Object proValue);
}

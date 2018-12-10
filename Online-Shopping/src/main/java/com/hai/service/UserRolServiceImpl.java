package com.hai.service;

import org.hibernate.SessionFactory;

import com.hai.dao.UserRoleDAOImpl;
import com.hai.idao.IUserRoleDAO;
import com.hai.iservice.IUserRoleService;
import com.hai.model.UserRole;

public class UserRolServiceImpl implements IUserRoleService {
	private IUserRoleDAO userRoleDAO;
	
	public UserRolServiceImpl(SessionFactory  sessionFactory) {
		userRoleDAO = new UserRoleDAOImpl(sessionFactory);
		
	}
	
	@Override
	public void save(UserRole userRole) {
		userRoleDAO.save(userRole);
		
	}

}

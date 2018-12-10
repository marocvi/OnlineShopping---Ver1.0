package com.hai.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.hai.dao.RoleDAOImpl;
import com.hai.idao.IRoleDAO;
import com.hai.iservice.IRoleService;
import com.hai.model.Role;
import com.hai.util.SessionFactoryBuilderUtil;

public class RoleServiceImpl implements IRoleService {
	private IRoleDAO roleDAO;
	public RoleServiceImpl(SessionFactory sessionFactory) {
		roleDAO = new RoleDAOImpl(sessionFactory);
	}
	
	@Override
	public Role findByRoleName(String roleName) {
	List<Role> listOfRoles = roleDAO.findByProperty("role",roleName);
		if(listOfRoles.size()>=1) {
			return listOfRoles.get(0);
		}
		return null;
	}
	
	public static void main(String[] args) {
		RoleServiceImpl roleService = new RoleServiceImpl(SessionFactoryBuilderUtil.getSessionFactory());
		System.out.println(roleService.findByRoleName("Normal"));
	}

}

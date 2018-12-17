package com.hai.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hai.dao.SubcategoryDAOImpl;
import com.hai.idao.ISubcategoryDAO;
import com.hai.iservice.ISubCategoryService;
import com.hai.model.SubCategory;

public class SubCategoryServiceImpl implements ISubCategoryService {
	private ISubcategoryDAO subCategoryDAO;
	private SessionFactory sessionFactory;
	private Logger LOGGER;

	public SubCategoryServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		subCategoryDAO = new SubcategoryDAOImpl(sessionFactory);
		LOGGER = Logger.getLogger(SubCategoryServiceImpl.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategory> getListOfSubCategoriesByCategory(int categoryID) {
		LOGGER.info("Call get List of SubCategory by Category");
		Session session = sessionFactory.openSession();
		List<SubCategory> listOfSubCategories =null;
		try {
		session.beginTransaction();
		String hql = "from SubCategory where Category_ID=:categoryID";
		Query<SubCategory> query = session.createQuery(hql);
		query.setParameter("categoryID", categoryID);
		listOfSubCategories = query.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("Can't get list of subcategories by category");
		}
		finally {
			session.close();
			LOGGER.info("Close session");
		}
		return listOfSubCategories;
	}

}

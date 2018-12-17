package com.hai.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hai.dao.CommentDAOImpl;
import com.hai.idao.ICommentDAO;
import com.hai.iservice.ICommentService;
import com.hai.model.Comments;

public class CommentServiceImpl implements ICommentService {
	private ICommentDAO commentDAO;
	private SessionFactory sessionFactory;
	private Logger LOGGER;

	public CommentServiceImpl(SessionFactory sessionFactory) {
		LOGGER = Logger.getLogger(CommentServiceImpl.class);
		this.sessionFactory = sessionFactory;
		commentDAO = new CommentDAOImpl(sessionFactory);
	}

	@Override
	public void saveComment(Comments comment) {
		LOGGER.info("Call save comment");
		commentDAO.save(comment);
	}

	@Override
	public List<Comments> getListOfCommentsByProduct(int product_id) {
		LOGGER.info("Call getListOfCommentsByProduct ");
		Session session = sessionFactory.openSession();
		List<Comments> listOfCommentsByProduct = null;
		session.beginTransaction();

		try {
			String hql = "from Comments where Product_ID =:product_id Order By createDate DESC";
			Query<Comments> query = session.createQuery(hql);
			// Set how many comment to be get, 10 latest comments will be shown
			query.setFirstResult(0);
			query.setMaxResults(10);
			query.setParameter("product_id", product_id);
			listOfCommentsByProduct = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Can't get list of Comments by Product");
		}
		finally {
			session.close();
		}
		return listOfCommentsByProduct;
	}

}

package com.hai.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hai.dao.WishlistDAOImpl;
import com.hai.idao.IWishlistDAO;
import com.hai.iservice.IWishListService;
import com.hai.model.Users;
import com.hai.model.Wishlist;

public class WishlistServiceImpl implements IWishListService {
	private SessionFactory sessionFactory;
	private IWishlistDAO wishlistDAO;
	private Logger LOGGER;

	public WishlistServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		wishlistDAO = new WishlistDAOImpl(this.sessionFactory);
		LOGGER = Logger.getLogger(WishlistServiceImpl.class);
	}

	@Override
	public void update(Wishlist wishlist) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Wishlist> getWishlistItemsByUser(Users user) {
		List<Wishlist> wishlist = wishlistDAO.findByProperty("user", user);
		return wishlist;
	}

	public List<Wishlist> getProductByPage(int startPosition, int maxResult, Users user) {

		// Open session
		Session session = sessionFactory.openSession();
		List<Wishlist> wishlists = null;

		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Wishlist> criteriaQuery = builder.createQuery(Wishlist.class);
			Root<Wishlist> categoryRoot = criteriaQuery.from(Wishlist.class);
			ParameterExpression<Object> paramValue = builder.parameter(Object.class);
			criteriaQuery.select(categoryRoot).where(builder.equal(categoryRoot.get("user"), paramValue));
			Query<Wishlist> query = session.createQuery(criteriaQuery);
			query.setParameter(paramValue, user);
			query.setFirstResult(startPosition);
			query.setMaxResults(12);
			wishlists = query.getResultList();
			session.getTransaction().commit();
			return wishlists;
		} catch (Exception e) {
			LOGGER.error("Can't fetch Wishlist in DB");
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;

		} finally {
			// Close session
			session.close();
		}

	}

	@Override
	public void save(Wishlist wishlist) {
		wishlistDAO.save(wishlist);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateSessionWishlist(HttpServletRequest request, HttpServletResponse response) {
		// Get list of wishlist items
		List<Wishlist> listOfWishlistItems = (List<Wishlist>) request.getSession().getAttribute("listOfWishlistItems");
		Users sessionUser = (Users) request.getSession().getAttribute("user");
		List<Wishlist> listOfWishlistItemsInDB = getWishlistItemsByUser(sessionUser);
		// Save to DB. If in DB already exist product , do nothing.
		if (listOfWishlistItems != null) {
			for (Wishlist sessionWishlist : listOfWishlistItems) {
				boolean checkExist = false;
				for (Wishlist dbWishlist : listOfWishlistItemsInDB) {
					if (sessionWishlist.getProduct().getId() != dbWishlist.getProduct().getId()) {
						checkExist=true;
						break;
					}
				}
				if(!checkExist) {
					sessionWishlist.setUser(sessionUser);
					save(sessionWishlist);
				}
			}

		}
		// Delete Cookie
		Cookie wishlistCookie = new Cookie("wishlistCookie", "");
		wishlistCookie.setMaxAge(0);
		response.addCookie(wishlistCookie);

		// Update session
		listOfWishlistItems = getWishlistItemsByUser(sessionUser);
		request.getSession().setAttribute("listOfWishlistItems", listOfWishlistItems);

	}

	@Override
	public void deleteByIdAndUser(int productID, Users user) {
		List<Wishlist> listOfWishlistItems = getWishlistItemsByUser(user);
		for (Wishlist wishlist : listOfWishlistItems) {
			if(wishlist.getProduct().getId()==productID) {
				deleteById(wishlist.getId());
				break;
			}
		}
		
	}

	@Override
	public void deleteById(int wishlistID) {
		wishlistDAO.delete(wishlistID);
		
	}

}

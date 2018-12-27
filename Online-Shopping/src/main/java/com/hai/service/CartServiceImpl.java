package com.hai.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hai.dao.CartDAOImpl;
import com.hai.idao.ICartDAO;
import com.hai.iservice.ICartService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Users;

public class CartServiceImpl implements ICartService {
	private ICartDAO  cartDAO;
	private Logger LOGGER;
	private SessionFactory sessionFactory;
	public CartServiceImpl(SessionFactory sessionFactory) {
		cartDAO = new CartDAOImpl(sessionFactory);
		LOGGER = Logger.getLogger(CartServiceImpl.class);
		this.sessionFactory = sessionFactory;
	}
	
	

	@Override
	public void update(Cart cart) {
		LOGGER.info("Call update Cart");
		cartDAO.update(cart);
	}

	@Override
	public double getMoneyTotal(List<CartDetail> listOfItems) {
		double moneyTotal = 0.0;
		for (CartDetail cartDetail : listOfItems) {
			moneyTotal += cartDetail.getMoney()*cartDetail.getAmount();
		}
		return moneyTotal;
	}

	@Override
	public void save(Cart cart) {
		LOGGER.info("Call save cart");
		cartDAO.save(cart);
	}

	@Override
	public void updateSessionCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		//Get Cart from guest
		Cart sessionCart = (Cart) session.getAttribute("cart");
		Cart databaseCart = user.getCart();
		//Check if user has cart then update else create new cart.
		if(sessionCart==null&&databaseCart!=null) {
			//Add databaseCart to session
			sessionCart = databaseCart;
			session.setAttribute("cart", sessionCart);
		}
		else if(sessionCart!=null&&databaseCart==null) {
			//Save sessionCart to DB
			sessionCart.setUser(user);
			cartDAO.save(sessionCart);
			
		}
		else if(sessionCart!=null&&databaseCart!=null) {
			
			//Get sessionCart and update to databse cart
			List<CartDetail> listOfCartDetailsFromUser = databaseCart.getCartDetails();
			boolean check=false;
			for(CartDetail carDetail: sessionCart.getCartDetails()) {
				for(CartDetail databaseCartDeatil: listOfCartDetailsFromUser) {
					if(carDetail.getProduct().getId()==databaseCartDeatil.getProduct().getId()) {
						databaseCartDeatil.setAmount(databaseCartDeatil.getAmount()+carDetail.getAmount());
						check=true;
						break;
					}
				}
				if(!check) {
					carDetail.setCart(databaseCart);
					databaseCart.getCartDetails().add(carDetail);
				}
			}
			databaseCart.setMoneyTotal(getMoneyTotal(listOfCartDetailsFromUser));
			update(databaseCart);
			session.setAttribute("cart", databaseCart);
			LOGGER.info("The id of cart" +databaseCart.getId());
		}
		
		//delete cookie cart
		Cookie cartCookie = new Cookie("cartCookie","");
		cartCookie.setMaxAge(0);
		response.addCookie(cartCookie);
	
	}



	@Override
	public void removeCartDetail(Cart cart, int productID) {
		LOGGER.info("Call ");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Cart dbcart = session.get(Cart.class, cart.getId());
			List<CartDetail> listOfItems = dbcart.getCartDetails();
			for(CartDetail cartDetail: listOfItems) {
				if(cartDetail.getProduct().getId()==productID) {
					listOfItems.remove(cartDetail);
					break;
				}
			}
			// Remvove item in session
			List<CartDetail> listOfCartDetails = cart.getCartDetails();
			for (CartDetail cartDetail : listOfCartDetails) {
				if (cartDetail.getProduct().getId() == productID) {
					listOfCartDetails.remove(cartDetail);
					break;
				}
			}
			// Change Price
			cart.setMoneyTotal(getMoneyTotal(listOfCartDetails));
			dbcart.setMoneyTotal(getMoneyTotal(listOfItems));
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.info("Can't remove CartDetail in DB");
			
		}
		finally {
			session.close();
			LOGGER.info("Close Session");
		}
	}



	@Override
	public void subtractItemAmount(Cart cart, int productID, int amount) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.update(cart);
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			LOGGER.warn("Can't subtract amount in DB");
		}
		finally {
			session.close();
			
		}
		
	}



	@Override
	public Cart getCartById(int cartID) {
		
		return cartDAO.findById(cartID);
	}

}


























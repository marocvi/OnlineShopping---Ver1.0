package com.hai.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.hai.dao.SpecialOfferDAOImpl;
import com.hai.idao.ISpecialOfferDAO;
import com.hai.iservice.ISpecialOfferService;
import com.hai.model.SpecialOffer;

public class SpecialOfferServiceImpl implements ISpecialOfferService{
	private ISpecialOfferDAO specialOfferDAO;
	private SessionFactory sessionFactory;
	
	public SpecialOfferServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		specialOfferDAO = new SpecialOfferDAOImpl(this.sessionFactory);
	}
	
	
	@Override
	public List<SpecialOffer> getListOfValidSpecialOffers() {
		
		return specialOfferDAO.findAll();
	}

}

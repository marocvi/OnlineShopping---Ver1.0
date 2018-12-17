package com.hai.service;

import java.util.List;

import com.hai.iservice.ICartService;
import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.sun.media.jfxmedia.MediaException;

public class CartServiceImpl implements ICartService {

	@Override
	public void update(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMoneyTotal(List<CartDetail> listOfItems) {
		double moneyTotal = 0.0;
		for (CartDetail cartDetail : listOfItems) {
			moneyTotal += cartDetail.getMoney()*cartDetail.getAmount();
		}
		return moneyTotal;
	}

}

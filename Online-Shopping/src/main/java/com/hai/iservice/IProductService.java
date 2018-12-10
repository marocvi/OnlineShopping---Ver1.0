package com.hai.iservice;

import java.util.List;

import com.hai.model.Product;

public interface IProductService  {
	
	public Integer getNumberOfProduct(int subCategoryID);
	public List<Product> getAllProduct();
	public List<Product> getProductByPage(int subCategoryID, int startPosition, int maxResult);
	
}
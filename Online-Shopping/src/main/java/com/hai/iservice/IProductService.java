package com.hai.iservice;

import java.util.List;

import com.hai.model.Product;

public interface IProductService  {
	
	public Integer getNumberOfProduct(int subCategoryID);
	public List<Product> getAllProduct();
	public List<Product> getProductByPage(int subCategoryID, int startPosition, int maxResult);
	public Product getProductByID(int productID);
	public List<Product> getListOfProductBySubCategory(int subCategoryID);
	public  double getValidPrice(Product product);
	public void update(Product product);
	/*
	 * There below function for config homepage
	 */
	public List<Product> getListOfGreatestProductsByBrand(String brand);
	public List<Product> getListOfNewestProductsByBrand(String brand);
	public List<String> getListOfProductBrand();
	/**
	 * This function is intended for searching product 
	 * @param keyName The key for searching.
	 * @return
	 */
	public List<Product> getListOfProductsByKeyName(String keyName);
	
	
	
}

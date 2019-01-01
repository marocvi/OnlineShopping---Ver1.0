package com.hai.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.hai.iservice.IProductService;
import com.hai.model.Product;
import com.hai.service.ProductServiceImpl;
import com.hai.util.SessionFactoryBuilderUtil;

public class ProductServiceTest {
	private static SessionFactory sessionFactory;
	private static IProductService productService;

	@BeforeClass
	public static void setup() {
		sessionFactory = SessionFactoryBuilderUtil.getSessionFactory();
		productService = new ProductServiceImpl(sessionFactory);
	}

//	@Test
	public void testGetNumberOfProduc() {

		productService.getAllProduct();

	}

//	@Test
	public void testListOfProductByPage() {
		assertEquals(12, productService.getProductByPage(4, 0, 12).size());

	}

//	@Test
	public void testGetListOfGreatestProductByBrand() {
		assertEquals(4, productService.getListOfGreatestProductsByBrand("sam sung").size());
	}

//	@Test
	public void testGetListNewestProductsByBrand() {
		assertEquals(4, productService.getListOfNewestProductsByBrand("sam sung").size());
	}

//	@Test
	public void testGetListOfBrands() {
		assertEquals(4, productService.getListOfProductBrand().size());
	}

	@Test
	public void testSearchProduct() {

		List<Product> products = productService.getListOfProductsByKeyName("p");
		String strJson = "{product:[";

		for (Product product : products) {
			strJson += "{name:\"" + product.getName() + "\",image:\"" + product.getProfileImage() + "\",price:\""
					+ productService.getValidPrice(product) + "\"},";

		}
		
		//Remove "," at the end of strJson;
		if(products.size()>0) {
			strJson= strJson.substring(0,strJson.length()-1);
		}
		strJson +="]}";
		System.out.println(strJson);

	}

}

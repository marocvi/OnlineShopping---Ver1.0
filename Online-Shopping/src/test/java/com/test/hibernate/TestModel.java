package com.test.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Category;
import com.hai.model.Comments;
import com.hai.model.Export;
import com.hai.model.ExportDetail;
import com.hai.model.Import;
import com.hai.model.ImportDetail;
import com.hai.model.Order;
import com.hai.model.OrderDetail;
import com.hai.model.Payment;
import com.hai.model.Price;
import com.hai.model.Product;
import com.hai.model.Role;
import com.hai.model.SubCategory;
import com.hai.model.Supplier;
import com.hai.model.UserRole;
import com.hai.model.Users;
import com.hai.model.Wishlist;

public class TestModel {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Users.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(UserRole.class).addAnnotatedClass(Comments.class)
				.addAnnotatedClass(Category.class).addAnnotatedClass(SubCategory.class).addAnnotatedClass(Product.class)
				.addAnnotatedClass(Supplier.class).addAnnotatedClass(Price.class).addAnnotatedClass(Wishlist.class)
				.addAnnotatedClass(Cart.class).addAnnotatedClass(CartDetail.class).addAnnotatedClass(Order.class)
				.addAnnotatedClass(OrderDetail.class).addAnnotatedClass(Export.class)
				.addAnnotatedClass(ExportDetail.class).addAnnotatedClass(Import.class)
				.addAnnotatedClass(ImportDetail.class).addAnnotatedClass(Payment.class);

		SessionFactory sf = configuration.buildSessionFactory();
//		
//		/**
//		 * Declaring
//		 */
//		
//		// User
//		Users user = new Users();
//		List<UserRole> user_userRoles= new ArrayList<UserRole>();
//		List<Comments> comments = new ArrayList<Comments>();
//		List<Wishlist> user_wishlists = new ArrayList<Wishlist>();
//		List<Cart> user_carts = new ArrayList<Cart>();
//		List<Order> paidUserOrders = new ArrayList<Order>();
//		List<Order> processUserOrders = new ArrayList<Order>();
//		List<Export> exports = new  ArrayList<Export>();
//		List<Import> user_imports = new ArrayList<Import>();
//
//		// Role
//		Role role = new Role();
//		List<UserRole> role_userRoles = new ArrayList<UserRole>();
//
//		// UserRole
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//
//		// Comment
//		Comments comment = new Comments();
//		comment.setUser(user);
//
//		// Guest
//		Guest guest = new Guest();
//		List<Wishlist> gue_wishlists = new ArrayList<Wishlist>();
//		List<Cart> gue_carts = new ArrayList<Cart>();
//		
//
//		// Category
//		Category category = new Category();
//		List<SubCategory> subcategorys = new ArrayList<SubCategory>();
//
//		// SubCategory
//		SubCategory subcategory = new SubCategory();
//		subcategory.setCategory(category);
//		List<Product> sub_products = new ArrayList<Product>();
//
//		// Supplier
//		Supplier supplier = new Supplier();
//		List<Product> sup_products = new ArrayList<Product>();
//		List<Import> sup_imports = new ArrayList<Import>();
//
//		// Product
//		Product product = new Product();
//		product.setSubcategory(subcategory);
//		List<Supplier> suppliers = new ArrayList<Supplier>();
//		List<Price> listOfPrice = new ArrayList<Price>();
//		List<Wishlist> pro_wishlists = new ArrayList<Wishlist>();
//		List<CartDetail>  pro_cartDetails = new ArrayList<CartDetail>();
//		List<OrderDetail> pro_orderDetails = new ArrayList<OrderDetail>();
//		List<ExportDetail> pro_exportDetails = new ArrayList<ExportDetail>();
//		List<ImportDetail> pro_importDetails = new ArrayList<ImportDetail>();
//
//		// Price
//		Price price = new Price();
//		price.setProduct(product);
//
//		// Wishlist
//		Wishlist wishList = new Wishlist();
//		wishList.setUser(user);
//		wishList.setGuest(guest);
//		wishList.setProduct(product);
//		
//		//Cart
//		Cart cart = new Cart();
//		cart.setGuest(guest);
//		cart.setUser(user);
//		List<CartDetail> cart_cartDetails = new ArrayList<CartDetail>();
//		
//		//Cart_Detail
//		CartDetail cartDetail = new CartDetail();
//		cartDetail.setCart(cart);
//		cartDetail.setProduct(product);
//		
//		//Order 
//		Order order = new Order();
//		order.setOrderUser(user);
//		order.setProcessUser(user);
//		List<OrderDetail> order_orderDetails = new ArrayList<OrderDetail>();
//		List<ExportDetail> ord_exportDetails  = new ArrayList<ExportDetail>();
//		
//		//Order Detail
//		OrderDetail orderDetail = new OrderDetail();
//		orderDetail.setOrder(order);
//		orderDetail.setProduct(product);
//		
//		//Export
//		Export export = new Export();
//		export.setUser(user);
//		List<ExportDetail> exp_exportDetails = new ArrayList<ExportDetail>();
//		
//		//ExportDetail
//		ExportDetail exportDetail = new ExportDetail();
//		exportDetail.setExport(export);
//		exportDetail.setOrder(order);
//		exportDetail.setProduct(product);
//		
//		//Imort 
//		Import productImport = new Import();
//		productImport.setUser(user);
//		productImport.setSupplier(supplier);
//		List<ImportDetail> imp_importDetails = new ArrayList<ImportDetail>();
//		
//		//ImportDetail
//		ImportDetail importDetail = new ImportDetail();
//		importDetail.setProductImport(productImport);
//		importDetail.setProduct(product);
//		
//		
//		
//		
//
//		/***
//		 * Maping
//		 */
//		// User-UserRole
//		user_userRoles.add(userRole);
//		user.setRoles(user_userRoles);
//
//		// User - Commnet
//		comments.add(comment);
//		user.setComments(comments);
//
//		// User- whishlist
//		user_wishlists.add(wishList);
//		user.setWishlists(user_wishlists);
//		
//		//User - Cart
//		user_carts.add(cart);
//		user.setCarts(user_carts);
//		
//		//User - Order
//		paidUserOrders.add(order);
//		user.setPaidUserOrders(paidUserOrders);
//		processUserOrders.add(order);
//		user.setProcessUserOrders(processUserOrders);
//		
//		//User - Export 
//		exports.add(export);
//		user.setExports(exports);
//		
//		//User - Import
//		user_imports.add(productImport);
//		user.setImports(user_imports);
//		
//		//Import - ImportDetail
//		imp_importDetails.add(importDetail);
//		productImport.setImportDetails(imp_importDetails);
//		
//		//Export - ExportDetail
//		exp_exportDetails.add(exportDetail);
//		export.setExportDetails(exp_exportDetails);
//		
//		//Order - OrderDetail
//		order_orderDetails.add(orderDetail);
//		order.setOrderDetails(order_orderDetails);
//		
//		//Order- ExportDetail
//		ord_exportDetails.add(exportDetail);
//		order.setExportDetails(ord_exportDetails);
//		
//
//		// Gest - wishlist
//		gue_wishlists.add(wishList);
//		guest.setWhishlists(gue_wishlists);
//		
//		//Guest - Cart 
//		gue_carts.add(cart);
//		guest.setCarts(gue_carts);
//		
//		//Cart - CartDetail
//		cart_cartDetails.add(cartDetail);
//		cart.setCartDetails(cart_cartDetails);
//		
//		//Product - Supplier
//		suppliers.add(supplier);
//		product.setSuppliers(suppliers);
//		
//		
//		//Prodcut - CartDetail
//		pro_cartDetails.add(cartDetail);
//		product.setCartDetails(pro_cartDetails);
//		
//		//product - OrderDetail
//		pro_orderDetails.add(orderDetail);
//		product.setOrderDetails(pro_orderDetails);
//		
//		//product - ExportDetail
//		pro_exportDetails.add(exportDetail);
//		product.setExportDetails(pro_exportDetails);
//		
//		//porduct - ImportDetail
//		pro_importDetails.add(importDetail);
//		product.setImportDetails(pro_importDetails);
//		
//
//		// Category-SubCategory
//		subcategorys.add(subcategory);
//		category.setSubCategorys(subcategorys);
//
//		// SubCategory - Product
//		sub_products.add(product);
//		subcategory.setProducts(sub_products);
//
//		// Supplier - Product
//		sup_products.add(product);
//		supplier.setProducts(sup_products);
//		
//		//Supplier - Import
//		sup_imports.add(productImport);
//		supplier.setImports(sup_imports);
//
//		// Product- Price
//		listOfPrice.add(price);
//		product.setPrices(listOfPrice);
//		
//		//Product - wishlist
//		pro_wishlists.add(wishList);
//		product.setWishlists(pro_wishlists);
//
//		// Role - UserRole
//		role_userRoles.add(userRole);
//		role.setUsers(role_userRoles);
//
		Session ss = sf.openSession();
		ss.beginTransaction();
		// Code go here;
//		ss.save(user);

		ss.getTransaction().commit();
		ss.close();

	}

}

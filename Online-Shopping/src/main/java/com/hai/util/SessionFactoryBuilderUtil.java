package com.hai.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Category;
import com.hai.model.Comments;
import com.hai.model.Export;
import com.hai.model.ExportDetail;
import com.hai.model.Guest;
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

public class SessionFactoryBuilderUtil {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Users.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(UserRole.class).addAnnotatedClass(Comments.class)
				.addAnnotatedClass(Category.class).addAnnotatedClass(SubCategory.class).addAnnotatedClass(Product.class)
				.addAnnotatedClass(Supplier.class).addAnnotatedClass(Price.class).addAnnotatedClass(Guest.class)
				.addAnnotatedClass(Wishlist.class).addAnnotatedClass(Cart.class).addAnnotatedClass(CartDetail.class)
				.addAnnotatedClass(Order.class).addAnnotatedClass(OrderDetail.class).addAnnotatedClass(Export.class)
				.addAnnotatedClass(ExportDetail.class).addAnnotatedClass(Import.class)
				.addAnnotatedClass(ImportDetail.class).addAnnotatedClass(Payment.class);
		return configuration.buildSessionFactory();
	}
}

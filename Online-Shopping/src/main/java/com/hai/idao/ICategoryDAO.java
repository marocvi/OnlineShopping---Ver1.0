package com.hai.idao;

import java.util.List;

import com.hai.model.Category;
/**
 * This is interface specifying how Category interact with database
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface ICategoryDAO {

	/**
	 * This method for saving a new Category to database
	 * 
	 * @param category The category object we want to save to database
	 */
	public boolean save(Category category);

	/**
	 * <b>Folow step:</b>
	 * <ul>
	 * <li> Load category from database</li>
	 * <li> Change its property value </li>
	 * <li> Update to database</li>
	 * </ui>
	 * 
	 * @param category
	 */
	public boolean update(Category category);

	/**
	 * Delete Category  in database
	 * 
	 * @param category The category object we want to delete
	 * @return if sucess return true else return false
	 */
	public boolean delete(Category cateogry);

	/**
	 * Delete Category  using identifier
	 * 
	 * @param categoryID This param determine identifier
	 * @return If sucess return true else return false
	 */
	public boolean delete(Integer cartgoryID);

	/**
	 *
	 * Find a Category using identifier
	 * 
	 * @param categoryID This is identifier
	 * @return The Category from DB which matchs to categoryID
	 */
	public Category findById(Integer categoryID);

	/**
	 * Find All Category are existed in DB
	 * 
	 * @return List of categories  
	 */
	public List<Category> findAll();

	/**
	 * Find all Cateogry which has column fit to the condition.
	 * 
	 * @param name     The name of property
	 * @param proValue The value of property
	 * @return List of Categories from database
	 */
	public List<Category> findByProperty(String name, Object proValue);

}

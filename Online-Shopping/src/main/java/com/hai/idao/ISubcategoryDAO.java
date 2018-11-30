package com.hai.idao;

import java.util.List;

import com.hai.model.SubCategory;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface ISubcategoryDAO {
	
	public boolean save(SubCategory subCategory);

	public boolean update(SubCategory subCategory);

	public boolean delete(SubCategory subCateogry);

	public boolean delete(Integer subCartgoryID);

	public SubCategory findById(Integer subCategoryID);

	public List<SubCategory> findAll();
	
	public List<SubCategory> findByProperty(String name, Object proValue);
}

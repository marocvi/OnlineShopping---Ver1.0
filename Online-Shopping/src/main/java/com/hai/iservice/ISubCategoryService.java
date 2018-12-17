package com.hai.iservice;

import java.util.List;

import com.hai.model.SubCategory;

public interface ISubCategoryService {
	
	public List<SubCategory> getListOfSubCategoriesByCategory(int categoryID);

}

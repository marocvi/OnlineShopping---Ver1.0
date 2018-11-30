package com.hai.idao;

import java.util.List;

import com.hai.model.Supplier;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface ISupplierDAO {
	
	public boolean save(Supplier supplier);

	public boolean update(Supplier supplier);

	public boolean delete(Supplier supplier);

	public boolean delete(Integer supplierID);

	public Supplier findById(Integer supplierID);

	public List<Supplier> findAll();
	
	public List<Supplier> findByProperty(String name, Object proValue);
}

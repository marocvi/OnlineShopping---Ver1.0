package com.hai.idao;

import java.util.List;

import com.hai.model.Guest;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IGuestDAO {
	
	public boolean save(Guest guest);

	public boolean update(Guest guest);

	public boolean delete(Guest guest);

	public boolean delete(Integer guestID);

	public Guest findById(Integer guestID);

	public List<Guest> findAll();
	
	public List<Guest> findByProperty(String name, Object proValue);
}

package com.hai.idao;

import java.util.List;

import com.hai.model.SpecialOffer;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface ISpecialOfferDAO {
	
	public boolean save(SpecialOffer specialOffer);

	public boolean update(SpecialOffer specialOffer);

	public boolean delete(SpecialOffer specialOffer);

	public boolean delete(Integer specialOfferID);

	public SpecialOffer findById(Integer specialOfferID);

	public List<SpecialOffer> findAll();
	
	public List<SpecialOffer> findByProperty(String name, Object proValue);
}

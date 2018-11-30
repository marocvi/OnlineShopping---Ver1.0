package com.hai.idao;

import java.util.List;

import com.hai.model.Comments;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface ICommentDAO {
	
	public boolean save(Comments comment);

	public boolean update(Comments comment);

	public boolean delete(Comments comment);

	public boolean delete(Integer commentID);

	public Comments findById(Integer commentID);

	public List<Comments> findAll();
	
	public List<Comments> findByProperty(String name, Object proValue);
}

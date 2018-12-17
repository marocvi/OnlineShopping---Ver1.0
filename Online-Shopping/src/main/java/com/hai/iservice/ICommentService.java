package com.hai.iservice;

import java.util.List;

import com.hai.model.Comments;

public interface ICommentService {

	public void saveComment(Comments comment);
	
	public List<Comments> getListOfCommentsByProduct(int product_id);
}

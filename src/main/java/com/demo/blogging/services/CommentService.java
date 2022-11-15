package com.demo.blogging.services;

import com.demo.blogging.payloads.CommentDto;

public interface CommentService {
	
//	to create comment
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void delete(Integer commentId);

}

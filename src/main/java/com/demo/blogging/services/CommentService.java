package com.demo.blogging.services;

import com.demo.blogging.payloads.CommentDto;
import com.demo.blogging.payloads.UserDto;

public interface CommentService {
	
//	to create comment
	CommentDto createComment(CommentDto commentDto, Integer postId);
	CommentDto getCommentById(Integer commentId);
	CommentDto createCommentByUser(CommentDto commentDto, Integer postId,String username);
	void delete(Integer commentId);

}

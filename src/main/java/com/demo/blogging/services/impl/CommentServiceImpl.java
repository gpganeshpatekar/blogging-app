package com.demo.blogging.services.impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.entities.Comment;
import com.demo.blogging.entities.Post;
import com.demo.blogging.entities.User;
import com.demo.blogging.exceptions.ResourceNotFoundException;
import com.demo.blogging.payloads.CommentDto;
import com.demo.blogging.payloads.UserDto;
import com.demo.blogging.repositories.CommentRepository;
import com.demo.blogging.repositories.PostRepository;
import com.demo.blogging.repositories.UserRepository;
import com.demo.blogging.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public CommentDto createCommentByUser(CommentDto commentDto, Integer postId,String username) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND+username));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}
	
	
	
	@Override
	public void delete(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepository.delete(comment);

	}
	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		return this.modelMapper.map(comment, CommentDto.class);
	}

}

package com.demo.blogging.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.entities.Comment;
import com.demo.blogging.entities.Post;
import com.demo.blogging.entities.SubComment;
import com.demo.blogging.entities.User;
import com.demo.blogging.exceptions.ResourceNotFoundException;
import com.demo.blogging.payloads.SubCommentDto;
import com.demo.blogging.repositories.CommentRepository;
import com.demo.blogging.repositories.PostRepository;
import com.demo.blogging.repositories.SubCommentRepository;
import com.demo.blogging.repositories.UserRepository;
import com.demo.blogging.services.SubCommentService;

@Service
public class SubCommentServiceImpl implements SubCommentService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private SubCommentRepository subCommentRepository;
	
	
	@Override
	public SubCommentDto replyToComment(SubCommentDto subCommentDto,String username,Integer postId, Integer commentId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND + username));
		SubComment subComment = this.modelMapper.map(subCommentDto, SubComment.class);
		subComment.setCreatedDateTime(LocalDateTime.now());
		subComment.setPost(post);
		subComment.setComments(comment);
		subComment.setUser(user);
		SubComment saveSubComment = this.subCommentRepository.save(subComment);
		return this.modelMapper.map(saveSubComment, SubCommentDto.class);
	}
	
	@Override
	public void deleteReplyOfComment(Integer subCommentId) {
		SubComment subComment = this.subCommentRepository.findById(subCommentId)
				.orElseThrow(() -> new ResourceNotFoundException("SubComment", "subCommentId", subCommentId));
		this.subCommentRepository.delete(subComment);

	}

}

package com.demo.blogging.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.payloads.ApiResponse;
import com.demo.blogging.payloads.CommentDto;
import com.demo.blogging.payloads.SubCommentDto;
import com.demo.blogging.services.CommentService;
import com.demo.blogging.services.SubCommentService;

@RestController
@RequestMapping("blogging-app/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@PreAuthorize("hasRole('NORMAL')")
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createCommentByUser(@RequestBody CommentDto commentDto,@PathVariable Integer postId, Principal principal){
		CommentDto createComment = this.commentService.createCommentByUser(commentDto, postId,principal.getName());
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("posts/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.delete(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.DELETE_COMMENT,true),HttpStatus.OK);
	}
	
	@GetMapping("posts/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId){
		CommentDto comment = this.commentService.getCommentById(commentId);
		return new ResponseEntity<CommentDto>(comment,HttpStatus.OK);
	}
	
	
	

}

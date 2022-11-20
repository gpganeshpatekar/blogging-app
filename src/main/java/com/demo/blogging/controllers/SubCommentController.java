package com.demo.blogging.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blogging.config.AppConstants;
import com.demo.blogging.payloads.ApiResponse;
import com.demo.blogging.payloads.SubCommentDto;
import com.demo.blogging.services.SubCommentService;

@RestController
@RequestMapping("blogging-app/")
public class SubCommentController {
	
	@Autowired
	private SubCommentService subCommentService;
	
//	add sub comment
	@PreAuthorize("hasRole('NORMAL')")
	@PostMapping("user/posts/{postId}/comments/{commentId}")
	public ResponseEntity<SubCommentDto> replyToComment(@RequestBody SubCommentDto subcommentDto, Principal principal,@PathVariable Integer postId, @PathVariable Integer commentId){
		SubCommentDto subComment = this.subCommentService.replyToComment(subcommentDto,principal.getName(),postId,commentId);
		return new ResponseEntity<SubCommentDto>(subComment,HttpStatus.CREATED);
	}
	
//	delete sub comment
	@PreAuthorize("hasRole('NORMAL')")
	@DeleteMapping("posts/comments/subcomment/{subCommentId}")
	public ResponseEntity<ApiResponse> deleteReplyOfComment(@PathVariable Integer subCommentId){
		this.subCommentService.deleteReplyOfComment(subCommentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.DELETE_COMMENT,true),HttpStatus.OK);
	}
	

}

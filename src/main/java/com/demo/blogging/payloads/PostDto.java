package com.demo.blogging.payloads;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.demo.blogging.entities.Category;
import com.demo.blogging.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	
	private Integer postId;

	@NotEmpty
	@Size(min = 4,message = "post title must be min of 4 characters")
	private String postTitle;

	@NotEmpty
	@Size(min = 10, message = "content must be min of 10 characters")
	private String postContent;

	private String postImageName;

	private CategoryDto category;

	private LocalDate addedDate;
	
	private LocalDate updatedDate;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	private Set<SubCommentDto> SubComments = new HashSet<>();
	

	
	

}
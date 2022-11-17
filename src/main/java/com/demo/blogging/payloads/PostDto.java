package com.demo.blogging.payloads;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.demo.blogging.entities.Category;
import com.demo.blogging.entities.User;

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
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostImageName() {
		return postImageName;
	}

	public void setPostImageName(String postImageName) {
		this.postImageName = postImageName;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public LocalDate getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	
	
	

}
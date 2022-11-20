package com.demo.blogging.payloads;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.demo.blogging.entities.Comment;
import com.demo.blogging.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCommentDto {
	
	private Integer subCommentId;
	
	@NotEmpty
	@Size(min = 10, message = "comment content must be min of 4 characters")
	private String subCommentContent;
	private LocalDateTime createdDateTime;

	private UserDto user;

}

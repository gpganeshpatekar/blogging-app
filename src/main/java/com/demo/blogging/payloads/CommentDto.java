package com.demo.blogging.payloads;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private Integer commentId;

	@NotEmpty
	@Size(min = 10, message = "comment content must be min of 4 characters")
	private String commentContent;
	
	private LocalDateTime createdDateTime;
	
	private UserDto user;

	

}

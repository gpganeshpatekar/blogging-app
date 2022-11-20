package com.demo.blogging.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sub_comments")
@Getter
@Setter
public class SubComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subCommentId;
	
	private String subCommentContent;
	
	@Column(name = "addedDate", insertable = false)
	private LocalDateTime createdDateTime;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comments;
	
	@OneToOne
	private User user;
	
	
	
	
	

}

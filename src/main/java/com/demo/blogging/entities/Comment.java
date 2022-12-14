package com.demo.blogging.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	private String commentContent;
	
	@Column(name = "addedDate", insertable = false)
	private LocalDateTime createdDateTime;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy = "comments", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private Set<SubComment> subComment = new HashSet<>();
	

}

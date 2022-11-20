package com.demo.blogging.entities;

import java.time.LocalDate;
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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(length = 100, nullable = false)
	private String postTitle;
	@Column(length = 10000)
	private String postContent;
	@Column(name = "post_image")
	private String postImageName;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate addedDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
	
	//mapping
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	//mapping
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<>();
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<SubComment> subComments = new HashSet<>();
	
	

	
	
	

	
	
	
	
	
	


}

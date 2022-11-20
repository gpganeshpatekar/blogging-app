package com.demo.blogging.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.blogging.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	Optional<Comment> findById(Integer commentId);

}

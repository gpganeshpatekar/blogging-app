package com.demo.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.blogging.entites.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}

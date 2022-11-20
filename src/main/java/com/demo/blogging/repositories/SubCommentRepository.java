package com.demo.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.blogging.entities.SubComment;

@Repository
public interface SubCommentRepository extends JpaRepository<SubComment, Integer> {

}

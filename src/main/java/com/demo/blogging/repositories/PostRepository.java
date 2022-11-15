package com.demo.blogging.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.blogging.entites.Category;
import com.demo.blogging.entites.Post;
import com.demo.blogging.entites.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	@Query("select p from Post p where p.postTitle like :key")
	List<Post> findByPostTitleContaining(@Param("key") String title);
	

}

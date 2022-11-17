package com.demo.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.blogging.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

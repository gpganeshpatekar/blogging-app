package com.demo.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.blogging.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

package com.demo.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.blogging.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}

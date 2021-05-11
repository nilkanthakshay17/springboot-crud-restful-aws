package com.axee.springboot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axee.springboot.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

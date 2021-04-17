package com.example.login.session.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.session.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

package com.tientt.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tientt.todoapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

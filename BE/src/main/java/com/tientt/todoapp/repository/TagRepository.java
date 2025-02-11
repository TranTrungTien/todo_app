package com.tientt.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tientt.todoapp.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}

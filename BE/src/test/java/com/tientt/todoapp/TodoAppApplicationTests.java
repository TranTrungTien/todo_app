package com.tientt.todoapp;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tientt.todoapp.entity.Task;
import com.tientt.todoapp.repository.TaskRepository;

@SpringBootTest
class TodoAppApplicationTests {
	@Autowired
	private TaskRepository taskRepository;

	@Test
	void getTasksByUser() {
		List<Task> tasks = taskRepository.getTasksByUserId(1L);
		tasks.forEach(System.out::println);
	}
}

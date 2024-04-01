package com.tientt.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tientt.todoapp.controller.request.TaskRequest;
import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.dto.TaskDTO;
import com.tientt.todoapp.service.TaskService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<BaseResponse<List<TaskDTO>>> getTasks() {
        BaseResponse<List<TaskDTO>> response = taskService.getTasks();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<BaseResponse<TaskDTO>> getTaskById(@PathVariable(name = "id") Long id) {
        BaseResponse<TaskDTO> response = taskService.getTaskById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/tasks")
    public ResponseEntity<BaseResponse<Long>> createTask(@RequestBody TaskRequest taskRequest) {
        BaseResponse<Long> response = taskService.createTask(taskRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<BaseResponse<Long>> editTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        BaseResponse<Long> response = taskService.editTask(id, taskRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<BaseResponse<Long>> deleteTodo(@PathVariable(name = "id") Long id) {
        BaseResponse<Long> response = taskService.deleteTask(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}

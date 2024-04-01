package com.tientt.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.dto.TaskDTO;
import com.tientt.todoapp.dto.UserDTO;
import com.tientt.todoapp.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<BaseResponse<List<UserDTO>>> getTags() {
        BaseResponse<List<UserDTO>> response = userService.getUsers();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<BaseResponse<UserDTO>> getUserById(@PathVariable(name = "id") Long id) {
        BaseResponse<UserDTO> response = userService.getUserById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/users/{id}/tasks")
    public ResponseEntity<BaseResponse<List<TaskDTO>>> getTasksByUserId(@PathVariable(name = "id") Long id) {
        BaseResponse<List<TaskDTO>> response = userService.getTasksByUserId(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}

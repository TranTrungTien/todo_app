package com.tientt.todoapp.service;

import java.util.List;

import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.dto.TaskDTO;
import com.tientt.todoapp.dto.UserDTO;
import com.tientt.todoapp.entity.Task;

public interface UserService {
    public BaseResponse<List<UserDTO>> getUsers();

    public BaseResponse<UserDTO> getUserById(Long id);

    public BaseResponse<List<TaskDTO>> getTasksByUserId(Long id);
}

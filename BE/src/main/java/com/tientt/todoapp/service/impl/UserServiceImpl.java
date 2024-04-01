package com.tientt.todoapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.dto.TaskDTO;
import com.tientt.todoapp.dto.UserDTO;
import com.tientt.todoapp.entity.Task;
import com.tientt.todoapp.mapper.AppMapper;
import com.tientt.todoapp.repository.TaskRepository;
import com.tientt.todoapp.repository.UserRepository;
import com.tientt.todoapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final AppMapper mapper;

    public UserServiceImpl(UserRepository userRepository, AppMapper mapper, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public BaseResponse<List<UserDTO>> getUsers() {
        var users = userRepository.findAll().stream().map(user -> mapper.mapEntityToDTO(user, UserDTO.class)).toList();
        return BaseResponse.successfulResponse("Successful", users);
    }

    @Override
    public BaseResponse<UserDTO> getUserById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return BaseResponse.successfulResponse("Successful", mapper.mapEntityToDTO(user.get(), UserDTO.class));
    }

    @Override
    public BaseResponse<List<TaskDTO>> getTasksByUserId(Long id) {
        var tasks = taskRepository.getTasksByUserId(id);

        return BaseResponse.successfulResponse("Successful",
                tasks.stream().map(task -> mapper.mapEntityToDTO(task,
                        TaskDTO.class))
                        .toList());
    }

}

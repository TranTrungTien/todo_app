package com.tientt.todoapp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tientt.todoapp.controller.request.TaskRequest;
import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.dto.TaskDTO;
import com.tientt.todoapp.entity.Tag;
import com.tientt.todoapp.entity.Task;
import com.tientt.todoapp.mapper.AppMapper;
import com.tientt.todoapp.repository.TagRepository;
import com.tientt.todoapp.repository.TaskRepository;
import com.tientt.todoapp.repository.UserRepository;
import com.tientt.todoapp.service.TaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final AppMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository,
            TagRepository tagRepository, AppMapper mapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    @Override
    public BaseResponse<List<TaskDTO>> getTasks() {
        return BaseResponse.successfulResponse("Successful",
                taskRepository.findAll().stream().map(task -> mapper.mapEntityToDTO(task, TaskDTO.class)).toList());
    }

    @Override
    public BaseResponse<TaskDTO> getTaskById(Long id) {
        var task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return BaseResponse.failedResponse("Task not exists");
        }
        return BaseResponse.successfulResponse("Successful",
                mapper.mapEntityToDTO(task.get(), TaskDTO.class));
    }

    @Override
    public BaseResponse<Long> createTask(TaskRequest taskRequest) {
        var task = buildTaskEntity(taskRequest, null);

        if (task.isEmpty())
            return BaseResponse.failedResponse("Failed to get user");
        return BaseResponse.successfulResponse("Successful", taskRepository.save(task.get()).getId());
    }

    @Override
    @Transactional
    public BaseResponse<Long> editTask(Long id, TaskRequest taskRequest) {
        var task = buildTaskEntity(taskRequest, id);
        if (task.isEmpty())
            return BaseResponse.failedResponse("Failed to get user");
        return BaseResponse.successfulResponse("Successful", taskRepository.save(task.get()).getId());
    }

    private Optional<Task> buildTaskEntity(TaskRequest taskRequest, Long id) {
        var user = userRepository.findById(taskRequest.getUserId());

        if (user.isEmpty()) {
            return Optional.empty();
        }

        List<Tag> tags = tagRepository.findAllById(taskRequest.getTagIds());

        return Optional.of(Task.builder().id(id).name(taskRequest.getName()).description(taskRequest.getDescription())
                .status(taskRequest.getStatus()).user(user.get()).tags(tags).startDate(taskRequest.getStartDate())
                .dueDate(taskRequest.getDueDate()).createdAt(new Date()).modifiedAt(new Date())
                .build());
    }

    @Override
    @Transactional
    public BaseResponse<Long> deleteTask(Long id) {
        taskRepository.deleteTaskTagsByTaskId(id);
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setUser(null);
            taskRepository.delete(task);
        }
        return BaseResponse.successfulResponse("Successful", id);
    }

}

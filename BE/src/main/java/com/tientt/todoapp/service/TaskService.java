package com.tientt.todoapp.service;

import java.util.List;

import com.tientt.todoapp.controller.request.TaskRequest;
import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.dto.TaskDTO;

public interface TaskService {
    public BaseResponse<List<TaskDTO>> getTasks();

    public BaseResponse<TaskDTO> getTaskById(Long id);

    public BaseResponse<Long> createTask(TaskRequest taskRequest);

    public BaseResponse<Long> editTask(Long id, TaskRequest taskRequest);

    public BaseResponse<Long> deleteTask(Long id);

}

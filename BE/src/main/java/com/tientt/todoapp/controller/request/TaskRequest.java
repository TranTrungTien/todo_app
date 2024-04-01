package com.tientt.todoapp.controller.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TaskRequest {
    private Long id;
    private String name;
    private String description;
    private int status;
    private Long userId;
    private List<Long> tagIds;
    private Date dueDate;
    private Date startDate;
}

package com.tientt.todoapp.dto.projection;

import java.util.Date;
import java.util.List;

import com.tientt.todoapp.entity.Tag;
import com.tientt.todoapp.entity.User;

public record TaskProjection(Long id, String name, String description, int status, User user, List<Tag> tags,
        Date startDate, Date dueDate) {

}

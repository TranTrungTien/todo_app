package com.tientt.todoapp.service;

import java.util.List;

import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.entity.Tag;

public interface TagService {
    public BaseResponse<List<Tag>> getTags();

    public BaseResponse<Tag> getTagById(Long id);
}

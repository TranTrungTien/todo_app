package com.tientt.todoapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.entity.Tag;
import com.tientt.todoapp.repository.TagRepository;
import com.tientt.todoapp.service.TagService;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public BaseResponse<List<Tag>> getTags() {
        return BaseResponse.successfulResponse("Successful", tagRepository.findAll());
    }

    @Override
    public BaseResponse<Tag> getTagById(Long id) {
        var tag = tagRepository.findById(id);
        if (tag.isEmpty()) {
            return BaseResponse.failedResponse("Failed to get tag");
        }
        return BaseResponse.successfulResponse("Successful", tag.get());
    }

}

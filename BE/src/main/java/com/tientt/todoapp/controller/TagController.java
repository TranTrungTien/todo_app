package com.tientt.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tientt.todoapp.controller.response.BaseResponse;
import com.tientt.todoapp.entity.Tag;
import com.tientt.todoapp.service.TagService;

@RestController
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public ResponseEntity<BaseResponse<List<Tag>>> getTags() {
        BaseResponse<List<Tag>> response = tagService.getTags();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<BaseResponse<Tag>> getTagById(@PathVariable(name = "id") Long id) {
        BaseResponse<Tag> response = tagService.getTagById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

}

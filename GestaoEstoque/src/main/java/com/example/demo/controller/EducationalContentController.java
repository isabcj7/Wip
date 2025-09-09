package com.example.demo.controller;

import com.example.demo.model.EducationalContent;
import com.example.demo.service.EducationalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class EducationalContentController {
    @Autowired
    private EducationalContentService contentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationalContent createContent(@RequestBody EducationalContent content) {
        return contentService.createContent(content);
    }

    @GetMapping
    public List<EducationalContent> getAllContents() {
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public EducationalContent getContentById(@PathVariable Integer id) {
        return contentService.getContentById(id);
    }

    @PutMapping("/{id}")
    public EducationalContent updateContent(@PathVariable Integer id, @RequestBody EducationalContent content) {
        return contentService.updateContent(id, content);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContent(@PathVariable Integer id) {
        contentService.deleteContent(id);
    }
}

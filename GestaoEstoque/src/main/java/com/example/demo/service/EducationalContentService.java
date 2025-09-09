package com.example.demo.service;

import com.example.demo.model.EducationalContent;
import com.example.demo.repository.EducationalContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EducationalContentService {
    @Autowired
    private EducationalContentRepository contentRepository;

    public EducationalContent createContent(EducationalContent content) {
        return contentRepository.save(content);
    }

    public List<EducationalContent> getAllContents() {
        return contentRepository.findAll();
    }

    public EducationalContent getContentById(Integer id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteúdo educativo não encontrado"));
    }

    public EducationalContent updateContent(Integer id, EducationalContent content) {
        EducationalContent existingContent = getContentById(id);
        existingContent.setTitulo(content.getTitulo());
        existingContent.setVideoUrl(content.getVideoUrl());
        existingContent.setDuracao(content.getDuracao());
        return contentRepository.save(existingContent);
    }

    public void deleteContent(Integer id) {
        if (!contentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteúdo educativo não encontrado");
        }
        contentRepository.deleteById(id);
    }
}

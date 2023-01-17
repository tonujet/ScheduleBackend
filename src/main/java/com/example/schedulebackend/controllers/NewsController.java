package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.dto.NewsDTO;
import com.example.schedulebackend.services.NewsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController{

    private  final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @PostMapping("/create")
    public ResponseEntity<Long> create(@Valid  @RequestBody NewsDTO newsDTO) {
        return ResponseEntity.ok(newsService.create(newsDTO));
    }


    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody NewsDTO newsDTO) {
        newsService.update(newsDTO);
        return ResponseEntity.ok("News is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(newsService.get(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        newsService.delete(id);
        return ResponseEntity.ok("User is deleted");
    }


    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

}

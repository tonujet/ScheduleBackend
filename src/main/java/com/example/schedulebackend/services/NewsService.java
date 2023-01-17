package com.example.schedulebackend.services;


import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.model.dto.NewsDTO;
import com.example.schedulebackend.model.entities.News;
import com.example.schedulebackend.repos.NewsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepo newsRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final String itemName;

    public NewsService(NewsRepo newsRepo,
                       ItemHandler itemHandler,
                       DbaService dbaService,
                       @Value("news") String itemName) {
        this.newsRepo = newsRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.itemName = itemName;
    }

    public long create(NewsDTO newsDTO) {
        itemHandler.handleIsIdNull(newsDTO.getId(), itemName);
        News news = newsRepo.save(News.toEntity(newsDTO));
        return news.getId();

    }

    public void update(NewsDTO newsDTO) {
        newsRepo.save(News.toEntity(newsDTO));
    }


    public NewsDTO get(long id) {
        News news = dbaService.get(newsRepo, itemName, id);
        return NewsDTO.toDTO(news);

    }


    public void delete(long id) {
        dbaService.get(newsRepo, itemName, id);
        newsRepo.deleteById(id);
    }

    public List<NewsDTO> getAll() {
        return newsRepo.findAll()
                .stream()
                .map(NewsDTO::toDTO)
                .toList();
    }

}

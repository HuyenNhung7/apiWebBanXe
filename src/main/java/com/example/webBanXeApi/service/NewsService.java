package com.example.webBanXeApi.service;

import com.example.webBanXeApi.models.News;
import com.example.webBanXeApi.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("date").descending());
        Page<News> pagedResult = newsRepository.findAll(paging);
        return pagedResult.toList();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    }

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public News updateNews(Long id, News news) {
        News existingNews = getNewsById(id);
        existingNews.setTitle(news.getTitle());
        existingNews.setContent(news.getContent());
        existingNews.setAuthor(news.getAuthor());
        existingNews.setDate(news.getDate());
        existingNews.setImage(news.getImage());
        existingNews.setCategory(news.getCategory());
        return newsRepository.save(existingNews);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

}
